package com.example.uthsmarttasksmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uthsmarttasksmvvm.View.homeScreen
import com.example.uthsmarttasksmvvm.View.routeScreen
import com.example.uthsmarttasksmvvm.ViewModel.taskViewModel
import com.example.uthsmarttasksmvvm.ui.theme.UTHSmartTasksMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val taskViewModel: taskViewModel = viewModel()
            UTHSmartTasksMVVMTheme {
                routeScreen(taskViewModel)
            }
        }
    }
}
