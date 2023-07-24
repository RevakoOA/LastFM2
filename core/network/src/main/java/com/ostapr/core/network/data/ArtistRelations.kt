package com.ostapr.core.network.data

import com.google.gson.annotations.SerializedName

/**
 * Response representation received from MusicBrainzService.
 * Primarily used to fetch artist image by mbid.
 *
 * Response example located in `assets/musicbrainz_artist.json`.
 */
data class ArtistRelations(val relations: List<RelationItem>) {

    companion object {
        private const val TYPE_IMAGE = "image"
        val includedTypes = listOf(TYPE_IMAGE)
    }
}

data class RelationItem(
    @SerializedName("url")
    val url: Url,
    @SerializedName("begin")
    val begin: Any,
    @SerializedName("type")
    val type: String,
    @SerializedName("type-id")
    val typeId: String,
    @SerializedName("target-credit")
    val targetCredit: String,
    @SerializedName("attributes")
    val attributes: List<Any>,
    @SerializedName("ended")
    val ended: Boolean,
    @SerializedName("end")
    val end: Any,
    @SerializedName("direction")
    val direction: String,
    @SerializedName("target-type")
    val targetType: String,
    @SerializedName("source-credit")
    val sourceCredit: String
)

data class Url(
    @SerializedName("resource")
    val resource: String,
    @SerializedName("id")
    val id: String
)