package com.ostapr.core.network.musicbrainz

import com.ostapr.core.network.data.ArtistRelations
import retrofit2.http.GET
import retrofit2.http.Path

interface MusicBrainzService {
    @GET("artist/{$ARTIST_MBID}?inc=url-rels&fmt=json")
    suspend fun getArtistRelated(@Path(ARTIST_MBID) artistMbid: String): ArtistRelations

    companion object {
        const val ARTIST_MBID = "artist_mbid"
        const val MUSIC_BRAINZ_BASE_URL = "https://musicbrainz.org/ws/2/"
    }
}