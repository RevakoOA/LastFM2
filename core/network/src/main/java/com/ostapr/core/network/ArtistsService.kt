package com.ostapr.core.network

import com.ostapr.core.network.data.Artist
import com.ostapr.core.network.data.ArtistDetailsResponse
import com.ostapr.core.network.data.TopArtists
import com.ostapr.core.network.data.TopArtistsResponse
import com.ostapr.model.ArtistDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ArtistsService {
    @GET("?method=geo.gettopartists&format=json")
    suspend fun getGeoTopArtists(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("limit") itemsPerPage: Int = 50
    ): TopArtistsResponse

    @GET("?method=artist.getinfo&format=json")
    suspend fun getArtistDetails(
        @Query("mbid") artistId: String,
        @Query("lang") language: String = "en"
    ): ArtistDetailsResponse

    companion object {
        const val LAST_FM_BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    }
}
