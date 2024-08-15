package com.example.cursofirebaselite.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.presentation.model.Artist
import com.example.cursofirebaselite.ui.theme.Black
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {

    val artist: State<List<Artist>> = viewModel.artist.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            "Popular artist",
            color = White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow{
            items(artist.value){
                ArtistItem(it)
            }
        }
    }
}

@Composable
fun ArtistItem(artist: Artist){

    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .clickable {
                    Toast
                        .makeText(context, artist.description, Toast.LENGTH_SHORT)
                        .show()
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
fun ArtistItemPreview(){
    val artist = Artist(
        "Daniel",
        "Diositoooo",
        "https://st2.depositphotos.com/2222024/5609/i/450/depositphotos_56093859-stock-photo-happy-little-orange-havanese-puppy.jpg",
       // emptyList()
    )
    ArtistItem(artist = artist)

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