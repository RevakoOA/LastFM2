package com.ostapr.core.network

import com.ostapr.core.network.data.Artist
import com.ostapr.core.network.data.TopArtists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistsApi {
    @GET("?method=geo.gettopartists")
    fun artists(@Query("country") country: String): Call<TopArtists>

    @GET("?method=artist.getinfo")
    fun artistDetails(@Query("artist")artistName: String): Call<Artist>
}
