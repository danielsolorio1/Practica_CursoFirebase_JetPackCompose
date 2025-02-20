package com.example.cursofirebaselite.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursofirebaselite.domain.CanAccessToApp
import com.example.cursofirebaselite.presentation.model.Artist
import com.example.cursofirebaselite.presentation.model.PlayList
import com.example.cursofirebaselite.presentation.model.Player
import com.example.cursofirebaselite.presentation.model.Programs
import com.example.cursofirebaselite.presentation.model.Suggestions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var canAccessToApp: CanAccessToApp = CanAccessToApp()
    private var database = Firebase.database // Realtime Database
    private var db: FirebaseFirestore = Firebase.firestore // Firestore database

    private val _artist = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artist

    private val _playlist = MutableStateFlow<List<PlayList>>(emptyList())
    val playlist: StateFlow<List<PlayList>> = _playlist

    private val _suggestions = MutableStateFlow<List<Suggestions>>(emptyList())
    val suggestions: StateFlow<List<Suggestions>> = _suggestions

    private val _program = MutableStateFlow<List<Programs>>(emptyList())
    val program: StateFlow<List<Programs>> = _program

    private val _player = MutableStateFlow<Player?>(null)
    val player: StateFlow<Player?> = _player

    private val _blockVersion = MutableStateFlow<Boolean>(false)
    val blockVersion:StateFlow<Boolean> = _blockVersion


    init {
//        repeat(20){
//            loadData()
//        }
        checkUserVersion()
        getArtist()
        getPlayer()
        getPlayLists()
        getSuggestions()
        getPrograms()
    }

    private fun checkUserVersion() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                canAccessToApp()
            }
            _blockVersion.value = !result

        }
    }

    //    private fun loadData() {
//        val random = (1..10000).random()
//        val artist = Artist(name = "Perrito $random", "Perrito chulo $random","https://images.pexels.com/photos/1458916/pexels-photo-1458916.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500")
//        db.collection("artist")
//            .add(artist)
//    }
    private fun getPlayer() {
        viewModelScope.launch {
            collectPlayer().collect { snapshot ->
                val player = snapshot.getValue(Player::class.java)
                _player.value = player
            }
        }
    }

    private fun getArtist() {
        viewModelScope.launch {
            val result: List<Artist> = withContext(Dispatchers.IO) {
                getAllArtists()
            }
            _artist.value = result
        }
    }

    private fun getPlayLists() {
        viewModelScope.launch {
            val result: List<PlayList> = withContext(Dispatchers.IO) {
                getAllPlaylists()
            }
            _playlist.value = result
        }
    }

    private fun getSuggestions() {
        viewModelScope.launch {
            val result: List<Suggestions> = withContext(Dispatchers.IO) {
                getAllSuggestions()
            }
            _suggestions.value = result
        }
    }

    private fun getPrograms() {
        viewModelScope.launch {
            val result: List<Programs> = withContext(Dispatchers.IO) {
                getAllPrograms()
            }
            _program.value = result
        }
    }

    private suspend fun getAllArtists(): List<Artist> {

        return try {
            db.collection("artist")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Artist::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }

    private suspend fun getAllPlaylists(): List<PlayList> {
        return try {
            db.collection("playlists")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(PlayList::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }

    private suspend fun getAllSuggestions(): List<Suggestions> {
        return try {
            db.collection("sugerencias")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Suggestions::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }

    private suspend fun getAllPrograms(): List<Programs> {
        return try {
            db.collection("programas")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Programs::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }

    private fun collectPlayer(): Flow<DataSnapshot> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                trySend(snapshot).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Error", error.message)
                close(error.toException())
            }

        }
        val ref = database.reference.child("player")
        ref.addValueEventListener(listener)

        awaitClose {
            ref.removeEventListener(listener)
        }
    }

    fun onPlaySelected() {
        if (player.value != null) {
            val currentPlayer = _player.value?.copy(play = !player.value?.play!!)
            val ref = database.reference.child("player")
            ref.setValue(currentPlayer)

        }
    }

    fun onCancelSelected() {
        val ref = database.reference.child("player")
        ref.setValue(null)
    }

    fun AddPlayer(artist: Artist) {
        val ref = database.reference.child("player")
        val player = Player(artist = artist, play = true)
        ref.setValue(player)
    }
}