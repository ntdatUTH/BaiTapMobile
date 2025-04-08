package com.example.uthsmarttasksmvvm.View

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasksmvvm.ViewModel.taskViewModel

@Composable
fun routeScreen(viewModel: taskViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            homeScreen(viewModel, navController)
        }
        composable("add"){
            addScreen(viewModel, navController)
        }
    }
}