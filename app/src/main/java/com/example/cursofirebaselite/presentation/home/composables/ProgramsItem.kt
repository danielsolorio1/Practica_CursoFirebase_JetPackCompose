package com.example.cursofirebaselite.presentation.home.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.Programs
import com.example.cursofirebaselite.ui.theme.colorCategory
import com.example.cursofirebaselite.ui.theme.colorPlaylist

@Composable
fun ProgramsItem(programs: Programs) {

    Column(
        modifier = Modifier.padding(horizontal = 6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 8.dp),
            model = programs.image,
            contentDescription = "Playlist image",
        )
        Text(
            text = programs.category.orEmpty(),
            color = colorCategory,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            text = programs.name.orEmpty(),
            color = colorPlaylist,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Text(
            text = programs.artist.orEmpty(),
            color = colorPlaylist,
            modifier = Modifier.padding(horizontal = 5.dp),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
        )
    }
}