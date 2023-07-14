package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig

class ArtistsRepository @Inject constructor(
    private val artistsApi: ArtistsApi
) {
    fun getTopArtists() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            TopArtistsPagingSource(artistsApi)
        }
    ).flow
}