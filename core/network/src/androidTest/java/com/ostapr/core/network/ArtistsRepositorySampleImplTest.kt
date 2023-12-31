package com.ostapr.core.network

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ostapr.model.Artist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
internal class ArtistsRepositorySampleImplTest {

    private lateinit var context: Context
    private lateinit var sampleRepo: ArtistsRepositorySampleImpl

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        sampleRepo = ArtistsRepositorySampleImpl(context)
    }

    @Test
    fun getTopArtists_flowIsCorrect() = runTest {
        val expected2ndArtist = Artist(
            name = "Boney M.",
            mbid = "5403bf6e-bc1d-4e62-b31f-926a2bf66a14",
            url = "https://www.last.fm/music/Boney+M.",
            images = mapOf(
                "small" to "https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png",
                "medium" to "https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png",
                "large" to "https://lastfm.freetls.fastly.net/i/u/174s/2a96cbd8b46e442fc41c2b86b821562f.png",
                "extralarge" to "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
                "mega" to "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
            ),
            rank = 2
        )

        val actualFlow = sampleRepo.getTopArtists()
        val firstArtists = actualFlow.asSnapshot { scrollTo(3) }

        assertThat(firstArtists[1]).isEqualTo(expected2ndArtist)
    }
}