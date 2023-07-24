package com.ostapr.core.network

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ostapr.core.common.SampleDataPagingSource
import com.ostapr.core.common.SampleDataParser
import com.ostapr.core.common.SampleDataParser.openFileStream
import com.ostapr.core.domain.ArtistsRepository
import com.ostapr.core.network.data.ArtistDetailsResponse
import com.ostapr.core.network.data.TopArtistsResponse
import com.ostapr.model.Artist
import com.ostapr.model.ArtistDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import java.io.InputStream
import java.lang.Exception

class ArtistsRepositorySampleImpl(private val appContext: Context) : ArtistsRepository {
    val pagingConfig = PagingConfig(pageSize = 5)

    override fun getTopArtists(coroutineScope: CoroutineScope): Flow<PagingData<Artist>> {
        val inputStream = openFileStream("top_artists.json", appContext)
        val topArtists = SampleDataParser.readSampleData(
            TopArtistsResponse::class.java,
            inputStream
        ).topArtists.artists.map { it.convertToDomainClass() }
        return Pager(config = pagingConfig, initialKey = 0) {
            SampleDataPagingSource(topArtists)
        }.flow
    }

    override suspend fun fetchArtistsDetails(
        artistId: String,
        coroutineScope: CoroutineScope
    ): ArtistDetails {
        val inputStream = openFileStream("artist.json", appContext)
        val artistDetails = SampleDataParser.readSampleData(
            ArtistDetailsResponse::class.java,
            inputStream
        )

        return artistDetails.artist.convertToDomainClass()
    }
}
