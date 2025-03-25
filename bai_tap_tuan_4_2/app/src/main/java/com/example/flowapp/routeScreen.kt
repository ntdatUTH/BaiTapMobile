package com.example.flowapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowapp.model.Task

@Composable
fun routeScreen(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { todoListScreen(navController) }
        composable("detail") { backStackEntry  ->
            val task = navController.previousBackStackEntry?.savedStateHandle?.get<Task>("task")
            if (task != null) {
                detailScreen(navController, task)
            }
        }
    }
}