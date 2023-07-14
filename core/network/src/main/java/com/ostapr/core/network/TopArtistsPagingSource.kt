package com.ostapr.core.network

import androidx.paging.PagingSource

class TopArtistsPagingSource(
    private val artistsApi: ArtistsApi
): PagingSource<Int, Artist>() {
}