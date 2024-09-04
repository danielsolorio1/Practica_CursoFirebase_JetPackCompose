package com.example.cursofirebaselite.presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class SearchViewModel : ViewModel() {

    private var db: FirebaseFirestore = Firebase.firestore // Firestore database

    private val _search = MutableStateFlow<List<Search>>(emptyList())
    val search: StateFlow<List<Search>> = _search

    init {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            val result: List<Search> = withContext(Dispatchers.IO) {
                getAllCards()
            }
            _search.value = result
        }

    }

    private suspend fun getAllCards(): List<Search> {
        return try {
            db.collection("cards")
                .orderBy("id", Query.Direction.ASCENDING) // Ordena por 'id' de forma ascendente
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Search::class.java)
                }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emptyList()
        }
    }


}