package com.example.cursofirebaselite

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursofirebaselite.presentation.home.HomeScreen
import com.example.cursofirebaselite.presentation.initial.InitialScreen
import com.example.cursofirebaselite.presentation.login.LoginScreen
import com.example.cursofirebaselite.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
) {

    NavHost(navController = navHostController, startDestination = "home"){
        composable("initial"){
            InitialScreen(
                navigateToLogin = {navHostController.navigate("login")},
                navigateToSignUp = {navHostController.navigate("signup")}
            )
        }
        composable("login"){
            LoginScreen(auth){
                navHostController.navigate("home")
            }
        }
        composable("signup"){
            SignUpScreen(auth)
        }
        composable("home"){
            HomeScreen()
        }
    }

}