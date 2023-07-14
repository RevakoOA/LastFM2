package com.ostapr.core.domain

import androidx.paging.PagingData
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    fun getTopArtists(): Flow<PagingData<Artist>>
}