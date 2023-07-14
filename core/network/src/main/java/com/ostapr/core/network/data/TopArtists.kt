package com.ostapr.core.network.data

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
    val totalPages: String
)