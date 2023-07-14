package com.ostapr.lastfm.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ostapr.model.Artist
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter

@Composable
fun artistsGrid(artists: List<Artist>) {
    LazyColumn {
        items(artists.size) { i ->
            artistItem(artist = artists[i])
        }
    }
}

@Composable
fun artistItem(artist: Artist) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = artist.pickImageUrl(), contentDescription = null, modifier = Modifier
                .size(120.dp)
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = artist.name, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Songs: ${artist.playCount}", style = MaterialTheme.typography.bodySmall)
    }
}