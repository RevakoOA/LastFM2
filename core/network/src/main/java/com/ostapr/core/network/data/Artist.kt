package com.ostapr.core.network.data

data class Artist(
    val images: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String,
    val rank: Int
)

data class Image(
    val url: String,
    val size: String
)