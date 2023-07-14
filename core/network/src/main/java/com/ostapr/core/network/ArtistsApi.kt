package com.ostapr.core.network

import com.ostapr.model.Artist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ArtistsApi {
    @GET("?method=geo.gettopartists")
    fun artists(@Query("country") country: String): Call<TopArtists>

    @GET("?method=artist.getinfo")
    fun artistDetails(@Query("artist")artistName: String): Call<Artist>
}
