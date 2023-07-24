package com.ostapr.lastfm.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ostapr.model.Artist
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

@Composable
fun artistsGrid(artists: List<Artist>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(80.dp)) {
        items(artists.size) { i ->
            artistItem(artist = artists[i])
        }
    }
}

@Composable
fun artistItem(artist: Artist) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        AsyncImage(
            model = artist.pickImageUrl(), contentDescription = null, modifier = Modifier
                .size(120.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = artist.name ?: "", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(widthDp = 160, heightDp = 250)
@Composable
fun Preview() {
    val artists = listOf(
        Artist(
            "Bee Gees",
            "bf0f7e29-dfe1-416c-b5c6-f9ebc19ea810",
            "bf0f7e29-dfe1-416c-b5c6-f9ebc19ea810",
            images = listOf("https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png"),
            imagesLoaded = true,
            rank = 1
        ),
        Artist(
            "Boney M.",
            "5403bf6e-bc1d-4e62-b31f-926a2bf66a14",
            "",
            images = listOf("https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png"),
            imagesLoaded = true,
            rank = 2
        )
    )
    artistsGrid(artists)
}