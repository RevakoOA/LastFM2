package com.ostapr.core.network.data

import com.google.gson.JsonDeserializer
import com.google.gson.annotations.SerializedName

internal data class TopArtistsResponse(
    val topArtists: TopArtists
)

internal data class TopArtists(
    @SerializedName("artist")
    val artists: List<Artist>,
    val attrs: Attrs,
)

internal data class Attrs(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String,
)
