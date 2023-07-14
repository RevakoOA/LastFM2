package com.ostapr.core.network.data

internal data class Artist(
    val images: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String,
    val rank: Int
)

internal data class Image(
    val url: String,
    val size: String
)