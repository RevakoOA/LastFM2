package com.ostapr.lastfm.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val artistsRepository: ArtistsRepository): ViewModel() {
    internal val artists: Flow<PagingData<Artist>> = artistsRepository.getTopArtists().cachedIn(viewModelScope)
}