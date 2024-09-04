package com.example.cursofirebaselite.presentation.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.model.Player

@Composable
fun PlayerComponent(
    player: Player,
    onPlaySelected: () -> Unit = {},
    onCancelSelected: () -> Unit = {},
    modifier: Modifier = Modifier // Aquí inicializamos modifier para evitar usarlo incorrectamente
) {
    val icon = if (player.play == true) R.drawable.ic_pause else R.drawable.ic_play
    val containerColor = Color(0x9908142C)


    // Ajusta el color con opacidad
    val playerColorWithOpacity =
        containerColor.copy(alpha = 1f) // Cambia el valor de alpha según lo que necesites

    Row(
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(playerColorWithOpacity),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier // No reutilizamos el modifier pasado por parámetro
                .size(70.dp)
                .padding(5.dp),
            model = player.artist?.image,
            contentDescription = "Artist image",
        )
        Column(
            modifier = Modifier.padding(horizontal = 8.dp) // Padding entre imagen y texto
        ) {
            Text(
                player.artist?.song.orEmpty(),
                color = White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                player.artist?.name.orEmpty(),
                color = White,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = icon),
            contentDescription = "icon",
            modifier = Modifier
                .size(40.dp)
                .clickable { onPlaySelected() }
        )
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier
                .size(40.dp)
                .clickable { onCancelSelected() }
        )
    }
}
