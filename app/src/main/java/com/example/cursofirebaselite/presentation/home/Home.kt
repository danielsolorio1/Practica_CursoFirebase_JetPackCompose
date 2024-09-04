package com.example.cursofirebaselite.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.home.composables.ArtistItem
import com.example.cursofirebaselite.presentation.home.composables.PlayerComponent
import com.example.cursofirebaselite.presentation.home.composables.PlaylistItem
import com.example.cursofirebaselite.presentation.home.composables.ProgramsItem
import com.example.cursofirebaselite.presentation.home.composables.SuggestionsItem
import com.example.cursofirebaselite.presentation.model.Artist
import com.example.cursofirebaselite.presentation.model.PlayList
import com.example.cursofirebaselite.presentation.model.Programs
import com.example.cursofirebaselite.presentation.model.Suggestions
import com.example.cursofirebaselite.ui.theme.Black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {
    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            val artist: State<List<Artist>> = viewModel.artist.collectAsState()
            val player by viewModel.player.collectAsState()
            val playlist: State<List<PlayList>> = viewModel.playlist.collectAsState()
            val suggestions: State<List<Suggestions>> = viewModel.suggestions.collectAsState()
            val programs: State<List<Programs>> = viewModel.program.collectAsState()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        stringResource(R.string.title_playlist),
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyRow {
                        items(playlist.value) {
                            PlaylistItem(playlist = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        stringResource(R.string.tittle_suggestions),
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyRow {
                        items(suggestions.value) {
                            SuggestionsItem(suggestions = it)
                        }
                    }
                    Text(
                        stringResource(R.string.title_programs),
                        color = White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    LazyRow {
                        items(programs.value) {
                            ProgramsItem(programs = it)
                        }
                    }
                    Spacer(modifier = Modifier.height(80.dp)) // Espacio para asegurar que no se solape con el player

                    Spacer(modifier = Modifier.height(80.dp)) // Espacio para asegurar que no se solape con el player
                }

                player?.let {
                    PlayerComponent(
                        it,
                        onPlaySelected = { viewModel.onPlaySelected() },
                        onCancelSelected = { viewModel.onCancelSelected() },
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Alinea el player en la parte inferior
                            .padding(bottom = 75.dp) // Asegura que esté justo encima de la barra
                    )
                }
            }
        }
    }
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