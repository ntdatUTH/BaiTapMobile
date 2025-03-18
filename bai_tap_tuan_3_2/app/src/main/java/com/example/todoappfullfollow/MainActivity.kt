package com.example.todoappfullfollow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoappfullfollow.ui.theme.TodoAppFullFollowTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppFullFollowTheme {
                AppNavigation()
//                Test()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("first") { Started_First(navController) }
        composable("second") { Started_Second(navController) }
        composable("third") { Started_Third(navController) }
        composable("end") { PageEnd(navController) }
    }
}

val imageMap = mapOf(
    "started_first" to R.drawable.started_first,
    "started_second" to R.drawable.started_second,
    "started_third" to R.drawable.started_third
)

@Composable
fun SplashScreen(navController: NavHostController) {
    var isVisible by remember { mutableStateOf(false) }

    // Hiệu ứng fade-in
    val alphaAnim by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )

    // Tự động chuyển trang sau 3 giây
    LaunchedEffect(Unit) {
        isVisible = true
        kotlinx.coroutines.delay(3000)
        navController.navigate("first") {
            popUpTo("splash") { inclusive = true } // Xóa splash khỏi backstack
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 70.dp),
        verticalArrangement = Arrangement.Center, // Căn giữa theo chiều dọc
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa theo chiều ngang
    ) {
        Image(
            painter = painterResource(id = R.drawable.logouth_splash),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .alpha(alphaAnim) // Hiệu ứng fade-in
        )
        Text(
            text = "UTH SmartTasks",
            fontFamily = FontFamily.Serif,
            fontSize = 24.sp,
            color = Color(android.graphics.Color.parseColor("#006EE9")),
        )
    }
}

@Composable
fun Started_First(navController: NavHostController){
    Started_Frame(navController, 1, "started_first", "Easy Time Management", "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first ")
}

@Composable
fun Started_Second(navController: NavHostController){
    Started_Frame(navController, 2, "started_second", "Increase Work Effectiveness","Time management and the determination of more important tasks will give your job statistics better and always improve")
}

@Composable
fun Started_Third(navController: NavHostController){
    Started_Frame(navController, 3, "started_third", "Reminder Notification","The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set")
}

@Composable
fun Dot(colorString: String) {
    Box (
        modifier = Modifier
            .size(8.dp) // Chấm to khi được chọn
            .background(Color(android.graphics.Color.parseColor(colorString)), shape = CircleShape)
            .padding(4.dp)
    )
    Spacer(modifier = Modifier.width(2.dp))
}

@Composable
fun ChangeDots( quantity: Int, index_dot: Int){
    for (i in 1..quantity){
        if(i == index_dot){
            Dot("#006EE9")
        }else{
            Dot("#D3D3D3")
        }
    }
}

@Composable
fun Started_Frame(navController: NavHostController,indexPage: Int,imageName: String, text1: String, text2: String){
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 30.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row() {
            Row (modifier = Modifier.padding(top = 15.dp)){
                ChangeDots(3,indexPage)
            }
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = {navController.navigate("end")}) {
                Text(
                    text = "skip",
                    color = Color(android.graphics.Color.parseColor("#006EE9"))
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
        val imgID = imageMap[imageName]
        if (imgID != null){
            Image(
                painter = painterResource(id = imgID), // Thay bằng tên file ảnh
                contentDescription = imageName,
                modifier = Modifier.size(330.dp)
            )}
        Text(
            text = text1,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = text2,
            modifier = Modifier.width(300.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            if(navController.previousBackStackEntry != null){
                IconButton(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier.weight(0.1f)
                        .background(Color(android.graphics.Color.parseColor("#006EE9")), shape = CircleShape)
                        .size(42.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White // Màu icon
                    )
                }}
            Button(
                onClick = {
                    when(indexPage){
                        1 -> navController.navigate("second")
                        2 -> navController.navigate("third")
                        3 -> navController.navigate("end")
                    }
                },
                modifier = Modifier.weight(0.7f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(android.graphics.Color.parseColor("#006EE9")) // Màu xanh biển nhạt
                )
            ) {
                Text(
                    text = if(indexPage == 3) "Get Started" else "Next"
                )
            }

        }

    }}

@Composable
fun PageEnd(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hoàn tất",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
    }
}