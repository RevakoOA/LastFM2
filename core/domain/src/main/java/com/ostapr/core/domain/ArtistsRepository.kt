package com.ostapr.core.domain

import androidx.paging.PagingData
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun getTopArtists(coroutineScope: CoroutineScope): Flow<PagingData<Artist>>

    suspend fun fetchArtistsDetails(artistId: String, coroutineScope: CoroutineScope): ArtistDetails?

}