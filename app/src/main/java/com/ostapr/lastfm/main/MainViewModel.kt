package com.ostapr.lastfm.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.model.Artist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val artistsRepository: ArtistsRepository) :
    ViewModel() {
    internal val artists: Flow<PagingData<Artist>> =
        artistsRepository.getTopArtists(viewModelScope).cachedIn(viewModelScope)
}