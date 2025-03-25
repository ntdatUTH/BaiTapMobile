package com.example.flowapp

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowapp.model.Task
import com.example.flowapp.viewModel.TaskViewModel

//@Preview (showBackground = true)
@Composable
fun todoListScreen(navController: NavController,taskViewModel: TaskViewModel = viewModel()){
    val tasks = taskViewModel.tasks.observeAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x33DCDCDC))
    ){
        Image(
            painter = painterResource(id = R.drawable.bgr),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 100.dp, y = 40.dp )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 25.dp, end = 25.dp, top = 50.dp, bottom = 25.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp) // Khoảng cách giữa các phần tử
        ) {
            topLayout()
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                if(tasks.value.isNotEmpty()) {
                    items(tasks.value) { task ->
                        itemLayout(task, navController)
                    }
                } else{
                    item { loadingScreen() }
                }
            }
            bottomBar()
        }

    }
}

@Composable
fun topLayout(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ){

            Image(
                painter = painterResource(id = R.drawable.logo_uth),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0x1A2196F3)),
            )
            Spacer(modifier = Modifier.width(13.dp))
            Column {
                Text(
                    text = "SmartTasks",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#2196F3"))
                )
                Text(
                    text = "A simple and efficient to-do app",
                    color = Color(android.graphics.Color.parseColor("#3991D8"))

                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Box {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "",
                        tint = Color(android.graphics.Color.parseColor("#FFA500")),
                        modifier = Modifier.size(35.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .offset(x = 25.dp, y = 13.dp)
                        .background(Color.Red, shape = CircleShape)

                )
            }
        }
    }
}

@Composable
fun bottomBar(){
    Box{
        var widthInDp by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        Row {
            BottomAppBar(
                containerColor = Color(0xFFFFFFFF),
//                containerColor = Color.DarkGray,
                modifier = Modifier
                    .height(60.dp)
                    .clip(RoundedCornerShape(30.dp))
//                    .onSizeChanged { size ->
//                        widthInDp = with(density) { size.width.toDp() } // Chuyển width từ px sang dp
//                    }
                ,
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    IconButton(
                        onClick = {},
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
            onClick = {},
            modifier = Modifier
                .offset(x = 147.dp, y= -15.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(Color(android.graphics.Color.parseColor("#2196F3")))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
                tint = Color.White
            )
        }
//        Text(
//            text = "${widthInDp}"
//        )
    }
}

@Composable
fun itemLayout(task: Task,navController: NavController){
    var isChecked by remember { mutableStateOf(false) }
    val parts = task.dueDate.split("T")
    val date = parts[0]  // 2024-03-26
    val time = parts[1].removeSuffix("Z")  // 09:00:00
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set("task", task)
                navController.navigate("detail")
                       },
        colors = CardDefaults.cardColors(
            containerColor = when(task.id%3){
                1 -> Color(android.graphics.Color.parseColor("#E1BBC1"))
                2 -> Color(0x4D8D9C0B)
                0 -> Color(android.graphics.Color.parseColor("#B7E9FF"))
                else -> Color.Gray
            }
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 7.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,    // Màu khi được chọn
                        checkmarkColor = Color.White   // Màu dấu tích bên trong
                    )
                )
                Column {
                    Text(
                        text = task.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    Text(
                        text = task.description,
                        fontSize = 15.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Status: ${task.status}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "${time} ${date}",
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun loadingScreen(){
    Card (
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(top = 40.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
            verticalArrangement = Arrangement.Center // Căn giữa theo chiều dọc
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_loading),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "No Tasks Yet!",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Stay productive—add something to do",
                fontSize = 17.sp
            )
        }
    }
}