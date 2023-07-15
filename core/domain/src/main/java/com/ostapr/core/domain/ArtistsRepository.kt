package com.ostapr.core.domain

import androidx.paging.PagingData
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun getTopArtists(): Flow<PagingData<Artist>>

    suspend fun fetchArtistsDetails(artistId: String): ArtistDetails?

}