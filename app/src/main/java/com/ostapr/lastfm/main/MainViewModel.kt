package com.ostapr.lastfm.main

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val artistsPagingSource: Lazy<>): ViewModel() {

    internal val artists: Flow<PagingData<Artist>> =
        Pager(PagingConfig(pageSize = 20)) {

        }
}