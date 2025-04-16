package com.example.uthsmarttasksmvvm.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasksmvvm.ViewModel.taskViewModel

@Composable
fun homeScreen(viewModel: taskViewModel, navController: NavController){
    val taskList by viewModel.tasks.observeAsState(emptyList()) // chuyển LiveData thành State
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x33DCDCDC))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp, vertical = 50.dp)
        ) {
            topLayoutHome(navController)
            LazyColumn {
                items(taskList){item ->
                    itemCard(item.id, item.task, item.desc)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            bottomBar(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topLayoutHome(navController: NavController){
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
            text = "List",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(android.graphics.Color.parseColor("#2196F3"))
        )
        IconButton(onClick = {navController.navigate("add")}) {
            Icon(
                imageVector = Icons.Default.AddCircleOutline,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .clip(shape = CircleShape)
                    .background(Color(android.graphics.Color.parseColor("#FFA500")))
            )
        }
    }
}

@Composable
fun itemCard(id: Int, task: String, desc: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {},
        colors = CardDefaults.cardColors(
            containerColor = when(id%3){
                1 -> Color(0x4D2196F3)
                2 -> Color(0xFFE1BBC1)
                0 -> Color(0xFFDDE1B6)
                else -> Color(0x4D2196F3)
            }
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 7.dp)
        ) {
            Text(
                text = task,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(
                text = desc,
                fontSize = 15.sp
            )
        }
    }
}

//@Preview (showBackground = true)
@Composable
fun bottomBar(navController: NavController){
    var size by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current
    Box(
        modifier = Modifier.fillMaxWidth()

    ){
        Row {
            BottomAppBar(
                containerColor = Color(0xFFFFFFFF),
//                containerColor = Color.DarkGray,
                modifier = Modifier
                    .height(60.dp)
                    .clip(RoundedCornerShape(30.dp))
                ,
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            size = coordinates.size // IntSize(width, height)
                        }
                    ,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    IconButton(
                        onClick = {navController.navigate("home")},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(45.dp),
                            tint = Color(android.graphics.Color.parseColor("#2196F3"))
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Home",
                            modifier = Modifier.size(35.dp),
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.width(50.dp))
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Description,
                            contentDescription = "Home",
                            modifier = Modifier.size(35.dp),
                            tint = Color.Gray
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Home",
                            modifier = Modifier.size(35.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
        IconButton(
            onClick = {navController.navigate("add")},
            modifier = Modifier
                .offset(x = (with(density) { size.width.toDp() })/2 - 24.dp, y= -15.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color(android.graphics.Color.parseColor("#2196F3")))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.White,
            )
        }
    }
}
