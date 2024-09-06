package com.example.cursofirebaselite.presentation.search.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.Search

// Define a list of colors
val colors = listOf(
    Color(0xFFE91E63),
    Color(0xFF3F51B5),
    Color(0xFFFFC107),
    Color(0xFF4CAF50),
    Color(0xFFFF5722)
)

@Composable
fun MusicCard(search: Search) {
    // Get a random color from the list
    val color = colors.random()

    Box(
        modifier = Modifier
            .padding(6.dp)
            .size(width = 180.dp, height = 90.dp)
            .background(color = color, shape = RoundedCornerShape(6.dp))
            .clip(RoundedCornerShape(6.dp))
    ) {
        // Title Text
        Text(
            text = "${search.name.orEmpty()}\n",
            color = White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .height(40.dp)
                .offset(x = -8.dp)
        )

        // Image that overlaps
        AsyncImage(
            model = search.image.orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .size(65.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 12.dp, y = -6.dp)
                .rotate(15f)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}