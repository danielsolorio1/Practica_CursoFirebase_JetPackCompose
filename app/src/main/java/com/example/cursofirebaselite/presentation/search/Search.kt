package com.example.cursofirebaselite.presentation.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.search.composables.AppTopBar
import com.example.cursofirebaselite.ui.theme.colorSearcher
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.cursofirebaselite.presentation.model.Search


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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            // Search bar
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(10)),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        stringResource(R.string.topAppbar_search),
                        color = colorSearcher,
                        fontWeight = FontWeight.Bold
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedPrefixColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    cursorColor = colorSearcher
                )
            )
            // Your LazyVerticalGrid or other content goes here
            Text(
                stringResource(R.string.xplorer_title),
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(search.value, key = { it.id ?: 0 }) { searchItem ->
                    Log.e("SearchScreen", "searchItem: $searchItem")
                    MusicCard(search = searchItem)
                }
                item {
                    Spacer(modifier = Modifier.height(75.dp)) // Ajusta la altura según lo que necesites
                }
            }


        }
    }
}

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





