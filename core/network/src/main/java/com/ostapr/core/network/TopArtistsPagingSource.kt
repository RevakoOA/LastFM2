package com.ostapr.core.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ostapr.model.Artist
import retrofit2.HttpException
import java.io.IOException


internal class TopArtistsPagingSource(
    private val artistsService: ArtistsService
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

            LoadResult.Page(
                data = response.topArtists.artists.map { it.convertToDomainClass() },
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

    companion object {
        const val UKRAINE_COUNTRY_CODE = "ukraine"
    }
}