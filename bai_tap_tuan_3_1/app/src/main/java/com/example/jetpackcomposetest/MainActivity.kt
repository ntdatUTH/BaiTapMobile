package com.example.jetpackcomposetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.jetpackcomposetest.ui.theme.JetpackComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTestTheme {
                RouteLayout()
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun RouteLayout(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "intro"){

        composable("intro"){
            GetLayout(navController)
        }
        //Màn hình UI Components List
        composable("home"){
            UIComponentsList(navController)
        }
        //Màn hình chi tiết từng thành phần
        composable("detail/{content_btn}"){
                backStackEntry ->
            val content_btn = backStackEntry.arguments?.getString("content_btn") ?: "No Data"
            Detail_component(navController, content_btn)
        }
    }
}

@Composable
fun GetLayout(navController: NavController){
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
            text = "Jetpack Compose",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center,
            modifier = Modifier.width(350.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {navController.navigate("home")},
            modifier = Modifier.width(300.dp).then(Modifier.padding(horizontal = 5.dp , vertical = 20.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        )
        {
            Text("I'm ready")

        }
    }
}
@Composable
fun Layout1 (navController: NavController,title: String, title1_btn: String, desc_btn1: String, title2_btn: String, desc_btn2: String){
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 20.dp)
    )
    Button(
        onClick = {
            val content_btn = title1_btn
            navController.navigate("detail/$content_btn")},
        modifier = Modifier.width(370.dp),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(android.graphics.Color.parseColor("#ADD8E6"))
        )
    ) {
        Column (
            modifier = Modifier.width(370.dp)
        ){
            Text(
                text = title1_btn,
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
            )
            Text(
                text = desc_btn1,
                style = TextStyle(color = Color.Black)
            )
        }
    }
    Button(
        onClick = {
            val content_btn = title2_btn
            navController.navigate("detail/$content_btn")},
        modifier = Modifier.width(370.dp),
        shape = RoundedCornerShape(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(android.graphics.Color.parseColor("#ADD8E6"))
        )) {
        Column (modifier = Modifier.width(370.dp)){
            Text(
                text = title2_btn,
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
            )
            Text(
                text = desc_btn2,
                style = TextStyle(color = Color.Black)
            )
        }
    }
}


@Composable
fun UIComponentsList(navController: NavController){
//    val buttonModifier = Modifier.
    CompositionLocalProvider(LocalTextStyle provides LocalTextStyle.current.copy(color = Color.Black)){
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxSize().then(Modifier.padding(vertical = 40.dp, horizontal = 20.dp))
    ) {
        Text(
            text = "UI Components List",
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        Layout1(navController,"Display","Text","Displays text","Image","Displays an image")
        Layout1(navController,"Input","TextField","Input field for text","PasswordField","Input field for passwords")
        Layout1(navController,"Layout","Column","Arranges elements vertically","Row","Arranges elements horizontally")
    }
}
}

fun handleButtonClick(buttonText: String) {
    Log.d("ButtonExample", "Button được nhấn: $buttonText")
}

//@Preview(showBackground = true)
@Composable
fun Detail_component(navController: NavController,content_btn: String){
    Column(modifier = Modifier.fillMaxSize().then(Modifier.padding(vertical = 40.dp))){
        Row(
            modifier = Modifier.align(Alignment.Start).then(Modifier.fillMaxWidth()),
        ) {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Blue
                )
            }
            Text(
                text = "Title",
                modifier = Modifier.fillMaxWidth().then(Modifier.padding(end = 40.dp)),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().then(Modifier.padding(bottom = 100.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (content_btn){
                "Text" -> Text(
                    text = buildAnnotatedString {
                        append("The ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) { append("the") }
                        append(" ")
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) { append("quick") }
                        append(" ")
                        withStyle(style = SpanStyle(color = Color.Yellow)) { append("Brown") }
                        append(" ")
                        append("j u m p s ") // Thêm khoảng cách giữa từng chữ
                        append(" ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("over") }
                        append(" ")
                        withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) { append("lazy") }
                        append(" dog.")
                    },
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                "Image" -> Image(
                    painter = painterResource(id = R.drawable.img_jetpack_compose),
                    contentDescription = "Ảnh từ drawable",
                    modifier = Modifier.size(350.dp)
                )
                "TextField" ->{
                    var text by remember { mutableStateOf("") }
                    TextField(  // Hiển thị TextField thay vì chỉ Text
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(text = "Nhập nội dung") },
                    modifier = Modifier.fillMaxWidth()
                )}
                "PasswordField" -> {
                    var password by remember { mutableStateOf("") }
                    var passwordVisible by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Mật khẩu") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = "Toggle password visibility")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                "Column" -> {
                    Column {
                        Text(text = "Dòng 1", color = Color.Black, fontSize = 24.sp)

                        Text(text = "Dòng 2", color = Color.Black, fontSize = 24.sp)
                } }
                "Row" ->
                    Row {
                        Text(text = "Cột 1", color = Color.Black, fontSize = 24.sp)
                        Text(text = "Cột 2", color = Color.Black, fontSize = 24.sp) }

            }

        }

    }
}
//@Composable
//fun GreetingPreview() {
//    JetpackComposeTestTheme {
//        Greeting("Android")
//    }
//}