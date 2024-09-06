package com.example.cursofirebaselite.presentation.library.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material.icons.filled.FormatLineSpacing
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.model.ArtistLibrary
import com.example.cursofirebaselite.ui.theme.fontOpenBold

@Composable
fun LibraryContent(padding: PaddingValues, library: State<List<ArtistLibrary>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.Black),

        ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FormatLineSpacing,
                contentDescription = "recents",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.library_title),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = fontOpenBold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.AutoAwesomeMosaic,
                contentDescription = "auto list",
                tint = Color.White
            )

        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(1500.dp),
            contentPadding = PaddingValues(2.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(library.value, key = { it.name ?: 0 }) { artistItem ->
                LibraryItem(artist = artistItem)
            }
            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }

    }
}
