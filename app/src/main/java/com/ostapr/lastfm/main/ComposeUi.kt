package com.ostapr.lastfm.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ostapr.model.Artist

@Composable
fun artistsGrid(artists: List<Artist>) {
    LazyColumn {
        items(artists.size) { i ->
            Text(artists[i].name)
        }
    }
}