package com.example.cursofirebaselite.presentation.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.model.Artist
import com.example.cursofirebaselite.presentation.model.Player
import com.example.cursofirebaselite.ui.theme.Black
import com.example.cursofirebaselite.ui.theme.Purple40
import com.example.cursofirebaselite.ui.theme.playerColor
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text(stringResource(R.string.app_name)) },
//                    scrollBehavior = scrollBehavior
//                )
//            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ){ padding ->
            val artist: State<List<Artist>> = viewModel.artist.collectAsState()
            val player by viewModel.player.collectAsState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(padding)
            ) {
                Text(
                    stringResource(R.string.title_home),
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
                LazyRow {
                    items(artist.value) {
                        ArtistItem(artist = it, onItemSelect = { viewModel.AddPlayer(it) })
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                player?.let {
                    PlayerComponent(
                        it,
                        onPlaySelected = { viewModel.onPlaySelected() },
                        onCancelSelected = { viewModel.onCancelSelected() }
                    )
                }
            }

        }
    }
}


@Composable
fun PlayerComponent(
    player: Player,
    onPlaySelected: () -> Unit = {},
    onCancelSelected: () -> Unit = {}
) {
    val icon = if (player.play == true) R.drawable.ic_pause else R.drawable.ic_play
    Row(
        Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(playerColor),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(70.dp)
                .padding(5.dp),
            model = player.artist?.image,
            contentDescription = "Artist image",
        )
        Column {
            Text(
                player.artist?.name.orEmpty(),
                modifier = Modifier.padding(horizontal = 0.dp),
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                player.artist?.song.orEmpty(),
                modifier = Modifier.padding(horizontal = 0.dp),
                color = White,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = icon),
            contentDescription = "icon",
            modifier = Modifier
                .size(40.dp)
                .clickable { onPlaySelected() })
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier
                .size(40.dp)
                .clickable { onCancelSelected() }
        )
    }
}

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
        Text(text = artist.name.orEmpty(), color = Color.White)
    }

}

@Preview
@Composable
fun ArtistItemPreview() {
    val artist = Artist(
        "Daniel",
        "Diositoooo",
        "https://st2.depositphotos.com/2222024/5609/i/450/depositphotos_56093859-stock-photo-happy-little-orange-havanese-puppy.jpg",
        // emptyList()
    )
    ArtistItem(artist = artist){}
}

//fun addArtist(db:FirebaseFirestore){
//    val random = (1..10000).random()
//    val artist = Artist(name = "Artista $random", numberOfSongs =  random)
//    db.collection("artists")
//        .add(artist)
//        .addOnSuccessListener {
//            Log.i("Firebase", "Success")
//        }
//        .addOnFailureListener{
//            Log.i("Firebase", "Fail")
//        }
//        .addOnCompleteListener {
//            Log.i("Firebase", "Complete")
//        }
//
//}