package com.ostapr.core.network.data

data class ArtistDetailsResponse(
    @SerializedName("artist")
    val artist: ArtistX
)