package com.ostapr.lastfm.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.network.ArtistsRepository
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MainViewModel(private val artistsRepository: ArtistsRepository): ViewModel() {
    internal val artists: Flow<PagingData<Artist>> = artistsRepository.getTopArtists().cachedIn(viewModelScope)
}