package com.example.flowapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flowapp.model.Attachment
import com.example.flowapp.model.Subtask
import com.example.flowapp.model.Task
import com.example.flowapp.viewModel.TaskViewModel

//@Preview (showBackground = true)
@Composable
fun detailScreen(navController: NavController,task: Task,taskViewModel: TaskViewModel = viewModel()){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 50.dp)
    ) {
        topLayoutDetail(navController)
        Text(
            text = task.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = task.description,
            )
        itemSpecial(task)
        Text(
            text = "Subtasks",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(13.dp))
        LazyColumn {
            items(task.subtasks){ subtask ->
                itemSubtasks(subtask)
            }
        }
        Text(
            text = "Attachments",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(13.dp))
        LazyColumn {
            items(task.attachments){ attachment ->
                itemAttachments(attachment)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topLayoutDetail(navController: NavController){
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
            text = "Detail",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(android.graphics.Color.parseColor("#2196F3"))
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Delete,
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
fun itemSpecial(task: Task){
    Card(
        modifier = Modifier.padding(vertical = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#E1BBC1"))
        )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            itemCon(1, task)
            itemCon(2, task)
            itemCon(3, task)
        }
    }
}

@Composable
fun itemCon(stt: Int, task: Task){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        ){
        Icon(
            //WorkspacePremium, EventNote
            imageVector = when(stt){
                1 -> Icons.Default.Widgets
                2 -> Icons.Default.EventNote
                3 -> Icons.Default.WorkspacePremium
                else -> Icons.Default.Widgets
            },
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(7.dp))
        Column {
            Text(
                text = when(stt){
                    1 -> "Category"
                    2 -> "Status"
                    3 -> "Priority"
                    else -> "Category"
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = when(stt){
                    1 -> task.category
                    2 -> task.status
                    3 -> task.priority
                    else -> task.category
                },
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun itemSubtasks(subtask: Subtask){
    var isChecked by remember { mutableStateOf(subtask.isCompleted) }
    Card(
        modifier = Modifier.padding(bottom = 13.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#E6E6E6"))
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,    // Màu khi được chọn
                    checkmarkColor = Color.White   // Màu dấu tích bên trong
                )
            )
            Text(
                text = subtask.title,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun itemAttachments(attachment: Attachment){
    Card(
        modifier = Modifier.padding(bottom = 13.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor("#E6E6E6"))
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AttachFile,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .rotate(-135f)
                    .graphicsLayer(scaleX = -1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            val context = LocalContext.current
            Text(
                text = attachment.fileName,
                fontSize = 17.sp,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(attachment.fileUrl))
                    context.startActivity(intent)
                }
            )
        }
    }
}