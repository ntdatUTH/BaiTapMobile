package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

object DataProvider {
    val columnList: List<String> = List(1_000_000) { index ->
        "${(index + 1).toString().padStart(2, '0')} | The only way to do great work is to love what you do."
    }
}
@Composable
fun lazyScreen(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 40.dp, horizontal = 20.dp)
    ) {
        topLayout("Lazy Column", navController)
        LazyColumn (
            modifier = Modifier.fillMaxSize().padding(top = 30.dp)
        ){  itemsIndexed(DataProvider.columnList){ i, text ->
            item(i, text, navController)
        }
        }
    }
}

@Composable
fun topLayout(title: String,navController: NavController){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {navController.popBackStack()},
            modifier = Modifier.size(50.dp)
            ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xE62196F3)),
            )
        }
        Text(
            text= title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color(android.graphics.Color.parseColor("#2196F3"))

        )

    }
}

//@Preview (showBackground = true)
@Composable
fun item(index: Int, text: String,navController: NavController){
    Card (
        colors = CardDefaults.cardColors(containerColor = Color(0x4D2196F3) )// Đặt màu nền
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                modifier = Modifier.weight(1f),
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = {navController.navigate("detail")},
                )  {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color.Black)
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}