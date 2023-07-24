package com.ostapr.core.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.GsonBuilder
import com.ostapr.core.common.SampleDataParser
import com.ostapr.core.network.data.ArtistRelations
import com.ostapr.core.network.data.RelationItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class ArtistRelationsDeserializerTest {

    @Test
    fun deserializer_createsObjectsCorrectly() {
        val expectedRelations = ArtistRelations(
            relations = listOf(
                RelationItem(
                    "image",
                    "https://upload.wikimedia.org/wikipedia/commons/f/ff/Gloria_Gaynor_(1976).jpg"
                ),
                RelationItem(
                    "image",
                    "https://upload.wikimedia.org/wikipedia/commons/f/ff/Gloria_Gaynor_2012_(cropped).jpg"
                )
            )
        )
        val gson = GsonBuilder().registerTypeAdapter(
            ArtistRelations::class.java,
            ArtistRelationsDeserializer(MusicBrainzUrlConvertor())
        ).create()

        val inputStream =
            SampleDataParser.openFileStream("musicbrainz_artist.json", appContext = null)
        val relations =
            SampleDataParser.readSampleData(ArtistRelations::class.java, inputStream, gson)

        assertThat(relations).isEqualTo(expectedRelations)
    }
}