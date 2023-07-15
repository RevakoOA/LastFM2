package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ArtistsRepositoryImpl @Inject constructor(
    private val artistsService: ArtistsService
): ArtistsRepository {
    override fun getTopArtists(): Flow<PagingData<Artist>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            TopArtistsPagingSource(artistsService)
        }
    ).flow

    override suspend fun fetchArtistsDetails(artistId: String): ArtistDetails? {
        return null
    }
}