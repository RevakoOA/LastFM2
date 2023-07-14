package com.ostapr.model

data class Artist(
    val name: String,
    val playCount: Int,
    val listeners: Int,
    val mbid: String,
    val imageUrls: List<String>,
) {
    /** Pick image url based on input criteria. */
    fun pickImageUrl(): String? = imageUrls.getOrNull(0)
}