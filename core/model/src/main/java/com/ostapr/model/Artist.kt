package com.ostapr.model

data class Artist(
    val name: String?,
    val mbid: String?,
    val url: String?,
    var images: List<String>,
    var imagesLoaded: Boolean,
    val rank: Int,
) {
    fun pickImageUrl(): String? = images.firstOrNull()
}

data class ArtistDetails(
    val name: String,
    val mbid: String,
    val url: String,
    val images: Map<String, String>,
    val stats: Stats,
    val similar: List<Artist>,
    val tags: List<Tag>,
    val bio: Bio,
) {
    data class Stats(val listeners: Int, val playCount: Int)
    data class Tag(val name: String, val url: String)
    data class Bio(val summary: String, val content: String, val published: String, val links: List<Link>) {
        data class Link(val text: String, val rel: String, val url: String)
    }
}