package com.example.cursofirebaselite.presentation.library.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.ArtistLibrary
import com.example.cursofirebaselite.ui.theme.fontArtist

@Composable
fun LibraryItem(artist: ArtistLibrary) {
    Row {

        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                Color.Black
            ),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(70.dp),
                model = artist.image,
                contentDescription = "Playlist image",
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 20.dp)
        ) {
            Text(
                text = artist.name.orEmpty(),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 5.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = artist.category.orEmpty(),
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 5.dp),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = fontArtist
            )
        }

    }
}