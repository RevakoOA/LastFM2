package com.ostapr.core.network.data

data class TopArtistsResponse(
    val topArtists: TopArtists
)

data class TopArtists(
    val artist: List<Artist>,
    val attrs: Attrs,
)



data class Attrs(
    val page: String,
    val perPage: String,
    val tag: String,
    val total: String,
    val totalPages: String
)