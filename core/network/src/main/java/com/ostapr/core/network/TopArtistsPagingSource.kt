package com.ostapr.core.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ostapr.core.network.data.RelationItem.Companion.TYPE_IMAGE
import com.ostapr.core.network.musicbrainz.MusicBrainzService
import com.ostapr.model.Artist
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll


internal class TopArtistsPagingSource(
    private val artistsService: ArtistsService,
    private val musicBrainzService: MusicBrainzService,
    private val coroutineScope: CoroutineScope,
) : PagingSource<Int, Artist>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        val page = params.key ?: 1
        return try {
            val response =
                artistsService.getGeoTopArtists(
                    UKRAINE_COUNTRY_CODE,
                    page = page,
                    itemsPerPage = params.loadSize
                )

            val artists = response.topArtists.artists.map { it.convertToDomainClass() }

            val imageUrlDeferredList = artists.map { artist ->
                coroutineScope.async { fetchImageUrl(artist) }
            }

            imageUrlDeferredList.awaitAll()

            LoadResult.Page(
                data = artists,
                nextKey = page + 1,
                prevKey = if (page == 1) null else page - 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        val anchorPos = state.anchorPosition ?: return 1 // load for the first time
        return state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
    }

    private suspend fun fetchImageUrl(artist: Artist) {
        val related = try {
            musicBrainzService.getArtistRelated(artist.mbid!!)
        } catch (e: HttpException) {
            null
        }
        artist.imagesLoaded = true
        artist.images = related?.relations?.filter { it.type == TYPE_IMAGE }?.map { it.url } ?: emptyList()
    }

    companion object {
        const val UKRAINE_COUNTRY_CODE = "ukraine"
    }
}