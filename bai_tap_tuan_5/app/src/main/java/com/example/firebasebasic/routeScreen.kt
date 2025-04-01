package com.example.firebasebasic

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient

@Composable
fun routeScreen(googleSignInClient: GoogleSignInClient, loginViewModel: LoginViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home",) {
        composable("home") { GoogleSignInScreen(navController, googleSignInClient, loginViewModel) }
        composable("profile/{userName}/{userEmail}") { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            val userEmail = backStackEntry.arguments?.getString("userEmail") ?: ""
            profileScreen(navController, userName, userEmail, googleSignInClient, loginViewModel) }
    }
}