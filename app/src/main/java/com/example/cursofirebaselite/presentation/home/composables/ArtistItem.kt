package com.example.cursofirebaselite.presentation.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.Artist
import com.example.cursofirebaselite.ui.theme.colorPlaylist


@Composable
fun ArtistItem(artist: Artist, onItemSelect: (Artist) -> Unit = {}) {

    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .clickable {
                    onItemSelect(artist)
                },
            model = artist.image,
            contentDescription = "Artist image",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = artist.name.orEmpty(),
            color = colorPlaylist,
            modifier = Modifier.padding(5.dp),
            fontSize = 15.sp
        )
    }

}