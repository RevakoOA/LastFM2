package com.ostapr.core.network

import android.content.Context
import androidx.paging.testing.asSnapshot
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ostapr.model.Artist
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
internal class ArtistsRepositorySampleImplTest {

    private val scope = TestScope()
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val sampleRepo: ArtistsRepositorySampleImpl = ArtistsRepositorySampleImpl(context)

    @Test
    fun getTopArtists_flowIsCorrect() = runTest {
        val expected2ndArtist = Artist(
            name = "Boney M.",
            mbid = "5403bf6e-bc1d-4e62-b31f-926a2bf66a14",
            url = "https://www.last.fm/music/Boney+M.",
            images = emptyList(),
            imagesLoaded = false,
            rank = 2
        )
        val expected2ndArtistUpdated = expected2ndArtist.copy(
            images = listOf(),
            imagesLoaded = true
        )
        val actualFlow = sampleRepo.getTopArtists(scope)
        val firstArtists = actualFlow.asSnapshot { scrollTo(3) }

        assertThat(firstArtists[1]).isEqualTo(expected2ndArtist)
    }
}