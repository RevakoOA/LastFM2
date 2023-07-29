package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.musicbrainz.MusicBrainzService
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ArtistsRepositoryImpl @Inject constructor(
    private val artistsService: ArtistsService,
    private val musicBrainzService: MusicBrainzService,
) : ArtistsRepository {
    override fun getTopArtists(coroutineScope: CoroutineScope): Flow<PagingData<Artist>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            TopArtistsPagingSource(artistsService, musicBrainzService, coroutineScope)
        }
    ).flow

    override suspend fun fetchArtistsDetails(
        artistId: String,
        coroutineScope: CoroutineScope
    ): ArtistDetails {
        val response = artistsService.getArtistDetails(artistId)
        return response.artist.convertToDomainClass()
    }
}
