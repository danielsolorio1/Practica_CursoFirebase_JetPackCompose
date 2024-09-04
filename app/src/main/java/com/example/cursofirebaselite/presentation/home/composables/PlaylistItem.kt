package com.example.cursofirebaselite.presentation.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.PlayList
import com.example.cursofirebaselite.ui.theme.colorPlaylist

@Composable
fun PlaylistItem(playlist: PlayList) {
    Column(
        modifier = Modifier.padding(horizontal = 6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 8.dp),
            model = playlist.image,
            contentDescription = "Playlist image",
        )
        Text(
            text = formatPlaylistNames(playlist.name.orEmpty()),
            color = colorPlaylist,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp
        )
    }
}

fun formatPlaylistNames(names: String): String {
    val items = names.split(",")
    val formattedNames = StringBuilder()

    for (i in items.indices) {
        formattedNames.append(items[i].trim())
        if (i % 2 == 1 && i != items.size - 1) {
            formattedNames.append("\n") // Agrega un salto de línea después de cada dos elementos
        } else if (i != items.size - 1) {
            formattedNames.append(", ")
        }
    }

    return formattedNames.toString()
}