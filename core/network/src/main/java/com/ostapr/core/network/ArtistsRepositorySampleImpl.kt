package com.ostapr.core.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.common.SampleDataPagingSource
import com.ostapr.core.common.SampleDataParser
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.data.ArtistDetailsResponse
import com.ostapr.core.network.data.TopArtistsResponse
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.flow.Flow

class ArtistsRepositorySampleImpl : ArtistsRepository {
    val pagingConfig = PagingConfig(pageSize = 5)

    override fun getTopArtists(): Flow<PagingData<Artist>> {
        val topArtists = SampleDataParser.readSampleData(
            TopArtistsResponse::class.java,
            "top_artists.json"
        ).topArtists.artists.map { it.convertToDomainClass() }
        return Pager(config = pagingConfig, initialKey = 0) {
            SampleDataPagingSource(topArtists)
        }.flow
    }

    override suspend fun fetchArtistsDetails(artistId: String): ArtistDetails {
        val artistDetails = SampleDataParser.readSampleData(
            ArtistDetailsResponse::class.java,
            fileName = "artist.json"
        )

        return artistDetails.artist.convertToDomainClass()
    }
}

