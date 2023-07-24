package com.ostapr.core.network.data

import com.google.gson.annotations.SerializedName
import com.ostapr.model.Artist as DomainArtist
import com.ostapr.model.ArtistDetails as DomainArtistDetails

internal data class Artist(
    val name: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    @SerializedName("image")
    val images: List<Image>,
    @SerializedName("@attr")
    val attrs: Attrs?
) {
    data class Attrs(val rank: Int)

    fun convertToDomainClass(): DomainArtist {
        val imagesMap = images.associate { it.size to it.url }
        return DomainArtist(
            name = name,
            mbid = mbid,
            url = url,
            // LastFM returns stubbed images, so we will try to load them by mbid from MusicBrainz
            // for more info check https://stackoverflow.com/questions/55978243/last-fm-api-returns-same-white-star-image-for-all-artists
            images = emptyList(),
            imagesLoaded = false,
            rank = attrs?.rank ?: -1
        )
    }
}

internal data class ArtistDetailsResponse(val artist: ArtistDetails)
internal data class ArtistDetails(
    val name: String,
    val mbid: String,
    val url: String,
    val images: List<Image>,
    val streamable: String,
    val ontour: String,
    val stats: Stats,
    val similar: Similar,
    val tags: Tags,
    val bio: Bio,
) {
    data class Stats(val listeners: Int, @SerializedName("playcount") val playCount: Int) {
        fun convertToDomainClass() = DomainArtistDetails.Stats(listeners, playCount)
    }

    data class Similar(@SerializedName("artist") val artists: List<Artist>)

    data class Tags(val tags: List<Tag>) {
        data class Tag(val name: String, val url: String) {
            fun convertToDomainClass() = DomainArtistDetails.Tag(name = name, url = url)
        }
    }

    data class Bio(
        val summary: String,
        val content: String,
        val published: String,
        val links: Links
    ) {

        fun convertToDomainClass() = DomainArtistDetails.Bio(
            summary = summary,
            content = content,
            published = published,
            links = links.links.map { it.convertToDomainClass() })

        data class Links(@SerializedName("link") val links: List<Link>) {
            data class Link(
                @SerializedName("#text") val text: String,
                val rel: String,
                val href: String
            ) {
                fun convertToDomainClass() =
                    DomainArtistDetails.Bio.Link(text = text, rel = rel, url = href)
            }
        }
    }

    fun convertToDomainClass(): DomainArtistDetails {
        val imagesMap = images.associate { it.size to it.url }
        return DomainArtistDetails(
            name = name,
            mbid = mbid,
            url = url,
            images = imagesMap,
            stats = stats.convertToDomainClass(),
            similar = similar.artists.map { it.convertToDomainClass() },
            tags = tags.tags.map { it.convertToDomainClass() },
            bio = bio.convertToDomainClass(),
        )
    }
}

data class Image(
    @SerializedName("#text")
    val url: String,
    val size: String
)