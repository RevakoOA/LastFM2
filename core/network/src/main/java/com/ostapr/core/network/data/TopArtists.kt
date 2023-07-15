package com.ostapr.core.network.data

import com.google.gson.JsonDeserializer

internal data class TopArtistsResponse(
    val topArtists: TopArtists
)

internal data class TopArtists(
    val artist: List<Artist>,
    val attrs: Attrs,
)

internal data class Attrs(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String,
)

class TopArtistsDeserializer : JsonDeserializer<>

,