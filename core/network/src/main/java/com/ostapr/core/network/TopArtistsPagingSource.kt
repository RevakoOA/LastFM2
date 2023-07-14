package com.ostapr.core.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ostapr.model.Artist


internal class TopArtistsPagingSource(
    private val artistsService: ArtistsService
): PagingSource<Int, Artist>() {
    override val jumpingSupported: Boolean
        get() = super.jumpingSupported
    override val keyReuseSupported: Boolean
        get() = super.keyReuseSupported

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        TODO("Not yet implemented")
    }
}