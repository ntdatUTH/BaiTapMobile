package com.example.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun rootScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Image(
            painter = painterResource(id = R.drawable.img_jetpack_compose),
            contentDescription = "Ảnh từ drawable",
            modifier = Modifier.size(400.dp).then(Modifier.padding(start = 0.dp, top = 125.dp))
        )
        Spacer(modifier = Modifier.weight(0.3f))
        Text(
            text = "Navigation",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app",
            textAlign = TextAlign.Center,
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Button(
            onClick = {navController.navigate("lazy")},
            modifier = Modifier.width(300.dp).padding(horizontal = 5.dp , vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        )
        {
            Text(
                text ="PUSH",
                fontSize = 17.sp
            )

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}