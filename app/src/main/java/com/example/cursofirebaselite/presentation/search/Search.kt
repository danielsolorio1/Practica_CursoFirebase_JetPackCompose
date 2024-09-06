package com.example.cursofirebaselite.presentation.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.cursofirebaselite.presentation.search.composables.AppTopBar
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.example.cursofirebaselite.presentation.model.Search
import com.example.cursofirebaselite.presentation.search.composables.SearchItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: SearchViewModel = SearchViewModel()) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val search: State<List<Search>> = viewModel.search.collectAsState()

    Scaffold(
        topBar = {
            AppTopBar(scrollBehavior)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        SearchItem(padding, search)
    }
}









