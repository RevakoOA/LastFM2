package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.common.SampleDataPagingSource
import com.ostapr.core.common.SampleDataParser
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.data.TopArtists
import com.ostapr.core.network.data.TopArtistsResponse
import com.ostapr.model.Artist
import kotlinx.coroutines.flow.Flow

class ArtistsRepositorySampleImpl : ArtistsRepository {
    val pagingConfig = PagingConfig(pageSize = 5)

    override fun getTopArtists(): Flow<PagingData<Artist>> {
        val topArtists = SampleDataParser.readSampleData(
            TopArtistsResponse::class.java,
            "top_artists.json"
        ).topArtists
        return Pager(config = pagingConfig, initialKey = 0) {
            SampleDataPagingSource<Artist>(topArtists.artist)
        }.flow
    }
}

