package com.example.cursofirebaselite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursofirebaselite.presentation.home.HomeScreen
import com.example.cursofirebaselite.presentation.initial.InitialScreen
import com.example.cursofirebaselite.presentation.login.LoginScreen
import com.example.cursofirebaselite.presentation.navigation.Navigation
import com.example.cursofirebaselite.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
) {
    // Check if the user is logged in
    val currentUser = auth.currentUser

    // If the user is not logged in, navigate to the initial screen
    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navHostController.navigate("initial") {
                // Ensure that the back stack is cleared to prevent returning to protected screens
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(navController = navHostController, startDestination = "navigation") {
        composable("initial") {
            InitialScreen(
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(auth) {
                navHostController.navigate("navigation") {
                    popUpTo("login") { inclusive = true } // Clear backstack to prevent going back to login
                }
            }
        }
        composable("signup") {
            SignUpScreen(auth)
        }
        composable("home") {
            HomeScreen()
        }
        composable("navigation") {
            Navigation()
        }
    }
}
