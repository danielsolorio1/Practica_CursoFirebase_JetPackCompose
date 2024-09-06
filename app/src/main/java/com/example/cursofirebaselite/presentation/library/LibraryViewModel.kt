package com.example.cursofirebaselite.presentation.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursofirebaselite.presentation.model.ArtistLibrary
import com.example.cursofirebaselite.presentation.model.Search
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LibraryViewModel: ViewModel() {

    private var db: FirebaseFirestore = Firebase.firestore // Firestore database

    private val _library = MutableStateFlow<List<ArtistLibrary>>(emptyList())
    val library : StateFlow<List<ArtistLibrary>> = _library

    init {
        getLibrary()
    }

    private fun getLibrary() {
        viewModelScope.launch {
            val result: List<ArtistLibrary> = withContext(Dispatchers.IO) {
                getAllCards()
            }
            _library.value = result
        }

    }

    private suspend fun getAllCards(): List<ArtistLibrary> {
        return try {
            db.collection("library")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(ArtistLibrary::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }

}