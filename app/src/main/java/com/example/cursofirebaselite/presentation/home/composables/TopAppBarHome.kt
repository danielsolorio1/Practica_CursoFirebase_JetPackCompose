package com.example.cursofirebaselite.presentation.home.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.R
import com.example.cursofirebaselite.ui.theme.Green
import com.example.cursofirebaselite.ui.theme.UnselectedField
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBarHome(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Circular avatar or icon on the left
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .background(Color(0xFFD1C4E9), CircleShape)
                        .clickable { Log.e("click", "clickkkkkkkkk") },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "D",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                ButtonGroup()

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ),
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun ButtonGroup() {
    // Estado para almacenar el botón seleccionado, puedes inicializarlo con un valor que quieras
    var selectedButton by remember { mutableStateOf("Total") }

    Row {
        // Botón "Total"
        Button(
            onClick = { selectedButton = "Total" },
            modifier = Modifier
                .width(75.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Total") Green else UnselectedField // Cambia el color según esté seleccionado o no
            )
        ) {
            Text(
                text = "Total",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Total") Color.Black else Color.White // Cambia el color del texto
            )
        }
        Spacer(modifier = Modifier.width(6.dp))

        // Botón "Música"
        Button(
            onClick = { selectedButton = "Música" },
            modifier = Modifier
                .width(85.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Música") Green else UnselectedField
            )
        ) {
            Text(
                text = "Música",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Música") Color.Black else Color.White
            )
        }
        Spacer(modifier = Modifier.width(6.dp))

        // Botón "Podcasts"
        Button(
            onClick = { selectedButton = "Podcasts" },
            modifier = Modifier
                .width(95.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Podcasts") Green else UnselectedField
            )
        ) {
            Text(
                text = "Podcasts",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Podcasts") Color.Black else Color.White
            )
        }
    }
}

@Composable
fun dr(){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            // Screen content
            Text(text = "Content", modifier = Modifier.padding(contentPadding))
        }
    }

}



/*
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTopBarHome(scrollBehavior: TopAppBarScrollBehavior) {
    // Estado para controlar el Drawer
    var showDrawer by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = if (showDrawer) DrawerValue.Open else DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                // Aquí puedes colocar el contenido del drawer
                Text(text = "Drawer Content", modifier = Modifier.padding(16.dp))
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Circular avatar or icon on the left
                                Box(
                                    modifier = Modifier
                                        .size(30.dp)
                                        .background(Color(0xFFD1C4E9), CircleShape)
                                        .clickable {
                                            // Al presionar la caja, cambiar el estado del drawer
                                            scope.launch {
                                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "D",
                                        color = Color.Black,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                ButtonGroup()
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Black
                        ),
                        scrollBehavior = scrollBehavior
                    )
                },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        text = { Text("Show drawer") },
                        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                        onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }
                    )
                }
            ) { contentPadding ->
                // Screen content
                Text(text = "Content", modifier = Modifier.padding(contentPadding))
            }
        }
    )
}

@Composable
fun ButtonGroup() {
    // Estado para almacenar el botón seleccionado, puedes inicializarlo con un valor que quieras
    var selectedButton by remember { mutableStateOf("Total") }

    Row {
        // Botón "Total"
        Button(
            onClick = { selectedButton = "Total" },
            modifier = Modifier
                .width(75.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Total") Green else UnselectedField // Cambia el color según esté seleccionado o no
            )
        ) {
            Text(
                text = "Total",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Total") Color.Black else Color.White // Cambia el color del texto
            )
        }
        Spacer(modifier = Modifier.width(6.dp))

        // Botón "Música"
        Button(
            onClick = { selectedButton = "Música" },
            modifier = Modifier
                .width(85.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Música") Green else UnselectedField
            )
        ) {
            Text(
                text = "Música",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Música") Color.Black else Color.White
            )
        }
        Spacer(modifier = Modifier.width(6.dp))

        // Botón "Podcasts"
        Button(
            onClick = { selectedButton = "Podcasts" },
            modifier = Modifier
                .width(95.dp)
                .height(33.dp),
            colors = ButtonDefaults.buttonColors(
                if (selectedButton == "Podcasts") Green else UnselectedField
            )
        ) {
            Text(
                text = "Podcasts",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = if (selectedButton == "Podcasts") Color.Black else Color.White
            )
        }
    }
}

 */