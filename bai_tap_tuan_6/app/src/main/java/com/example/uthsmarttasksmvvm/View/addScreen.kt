package com.example.uthsmarttasksmvvm.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasksmvvm.ViewModel.taskViewModel

@Composable
fun addScreen(viewModel: taskViewModel, navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 50.dp),
    ) {
        topLayoutAdd(navController)
        Text(
            text = "Task",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
        var textTask by remember { mutableStateOf("") }
        OutlinedTextField(
            value = textTask,
            onValueChange = {textTask = it},
            placeholder = {
                Text(
                    text ="Do homework",
                    color = Color.Gray
                )
                          },
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        var textDesc by remember { mutableStateOf("") }
        OutlinedTextField(
            value = textDesc,
            onValueChange = {textDesc = it},
            placeholder = {
                Text(
                    text = "Don't give up",
                    color = Color.Gray
                )
                          },
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )
        val context = LocalContext.current
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if(viewModel.checkData(textTask, textDesc, context))
                        navController.navigate("home")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                ),
                modifier = Modifier.width(100.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "Add",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topLayoutAdd(navController: NavController){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        CompositionLocalProvider(
            LocalMinimumTouchTargetEnforcement provides false,
        ) {
            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .padding(0.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xE62196F3))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }

        Text(
            text = "Add New",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(android.graphics.Color.parseColor("#2196F3"))
        )

        Text("")
    }
}