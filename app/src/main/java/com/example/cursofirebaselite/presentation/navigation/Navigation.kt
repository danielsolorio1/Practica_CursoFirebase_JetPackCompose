package com.example.cursofirebaselite.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.presentation.home.HomeScreen
import com.example.cursofirebaselite.presentation.home.Screen
import com.example.cursofirebaselite.presentation.library.LibraryScreen
import com.example.cursofirebaselite.presentation.premium.Premium
import com.example.cursofirebaselite.presentation.search.SearchScreen
import com.example.cursofirebaselite.ui.theme.colorPlaylist
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val auth = FirebaseAuth.getInstance()
    val homeTab = TabBarItem(
        title = stringResource(id = R.string.home),
        selectedIcon = painterResource(id = R.drawable.home_selected) ,
        unselectedIcon = painterResource(id = R.drawable.home_unselected)
    )
    val searchTab = TabBarItem(
        title = stringResource(id = R.string.search),
        selectedIcon = painterResource(id = R.drawable.search_selected),
        unselectedIcon = painterResource(id = R.drawable.search_unselected),
    )
    val libraryTab = TabBarItem(
        title = stringResource(id = R.string.library),
        selectedIcon = painterResource(id = R.drawable.library_selected),
        unselectedIcon = painterResource(id = R.drawable.library_unselected)
    )
    val premiumTab = TabBarItem(
        title = stringResource(id = R.string.premium),
        selectedIcon = painterResource(id = R.drawable.spo),
        unselectedIcon = painterResource(id = R.drawable.spo)
    )

    // creating a list of all the tabs
    val tabBarItems = listOf(homeTab, searchTab, libraryTab, premiumTab)

    val navController = rememberNavController()

    Screen {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            bottomBar = { TabView(tabBarItems, navController) },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) {
            NavHost(navController = navController, startDestination = homeTab.title) {
                composable(homeTab.title) {
                    HomeScreen()
                }
                composable(searchTab.title) {
                    SearchScreen()
                }
                composable(libraryTab.title) {
                    LibraryScreen()
                }
                composable(premiumTab.title) {
                    Premium(auth, navController)
                }
            }
        }

    }

}

data class TabBarItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val badgeAmount: Int? = null
)

@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)
    }
   val containerColor = Color(0xE6000000)
    NavigationBar(
        containerColor = containerColor,
    ) {
        // looping over each tab to generate the views and navigation for each item
        tabBarItems.forEachIndexed { index, tabBarItem ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(tabBarItem.title)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon = tabBarItem.selectedIcon,
                        unselectedIcon = tabBarItem.unselectedIcon,
                        title = tabBarItem.title,
                        badgeAmount = tabBarItem.badgeAmount
                    )
                },
                label = { Text(tabBarItem.title, color = colorPlaylist) })
        }
    }
}

// This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: Painter,
    unselectedIcon: Painter,
    title: String,
    badgeAmount: Int? = null
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
        Icon(
            painter = if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            },
            contentDescription = title,
            modifier = Modifier.size(25.dp)
        )
    }
}

// This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}
