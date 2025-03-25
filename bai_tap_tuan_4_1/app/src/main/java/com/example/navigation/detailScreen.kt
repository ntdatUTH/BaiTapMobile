package com.example.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun detailScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        topLayout("Detail", navController)
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "\"The only way to do great work is to love what you do\"",
            modifier = Modifier
                .width(230.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(450.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xE62196F3) )// Đặt màu nền

        ) {
            Text(
                text = "\"The only way to do great work is to love what you do.\"",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp, start = 35.dp , end = 35.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 49.sp,
                lineHeight = 55.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Steve Jobs",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold

            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "http://quotes.thisgrandpablogs.com/",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {navController.navigate("home")},
            modifier = Modifier
                .width(250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(android.graphics.Color.parseColor("#2196F3"))
            )
        ) {
            Text(
                text = "BACK TO ROOT",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}