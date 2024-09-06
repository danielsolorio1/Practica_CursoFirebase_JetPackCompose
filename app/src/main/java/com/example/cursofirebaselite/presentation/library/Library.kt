package com.example.cursofirebaselite.presentation.library

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.cursofirebaselite.presentation.library.composables.AppTopBarLibrary
import com.example.cursofirebaselite.presentation.library.composables.LibraryContent
import com.example.cursofirebaselite.presentation.model.ArtistLibrary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(viewModel: LibraryViewModel = LibraryViewModel()) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val library: State<List<ArtistLibrary>> = viewModel.library.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarLibrary(scrollBehavior)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        LibraryContent(padding = padding, library = library)
    }

}