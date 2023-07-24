package com.ostapr.core.network.data

import com.google.gson.annotations.SerializedName

/**
 * Response representation received from MusicBrainzService.
 * Primarily used to fetch artist image by mbid.
 *
 * Response example located in `assets/musicbrainz_artist.json`.
 */
data class ArtistRelations(val relations: List<RelationItem>)

data class RelationItem(
    val type: String,
    val url: String,
) {
    companion object {
        const val TYPE_IMAGE = "image"
        val INCLUDED_TYPES = listOf(TYPE_IMAGE)
    }
}
