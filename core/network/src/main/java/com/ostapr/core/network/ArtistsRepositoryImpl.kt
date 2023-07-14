package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

internal class ArtistsRepositoryImpl constructor(
    private val artistsService: ArtistsService
): ArtistsRepository {
    fun getTopArtists(): Flow<PagingData<Artist>> = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            TopArtistsPagingSource(artistsService)
        }
    ).flow
}