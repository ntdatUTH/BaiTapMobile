package com.example.firebasebasic

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException

//@Preview (showBackground = true)
@Composable
fun GoogleSignInScreen(
    navController: NavController,
    googleSignInClient: GoogleSignInClient,
    loginViewModel: LoginViewModel
) {
//    val userEmail by loginViewModel.userEmail.collectAsState()
//    val userName by loginViewModel.userName.collectAsState()
//    val isLoginSuccess by loginViewModel.isLoginSuccess.collectAsState()
// Tạo launcher một lần và nhớ trạng thái
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("GoogleSignIn", "Google Sign-In thành công, ID Token: ${account.idToken}")
//                firebaseAuthWithGoogle(account.idToken!!)
                loginViewModel.setLoginState(true, account.email ?: "", account.displayName ?: "") // Lưu email vào ViewModel
                navController.navigate("profile/${account.displayName}/${account.email}")
            } catch (e: ApiException) {
                Log.e("GoogleSignIn", "Đăng nhập thất bại: ${e.message}")
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bgr),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(450.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_uth),
                    contentDescription = "",
                    modifier = Modifier
                        .size(175.dp) // Kích thước thật của ảnh
                        .clip(RoundedCornerShape(13.dp))
                        .background(Color(0xFFD5EDFF)) // Màu nền của ảnh (nhưng chỉ áp dụng đúng kích thước ảnh)
                        .padding(25.dp)

                )

                Text(
                    text = "SmartTasks",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#2196F3")),
                    modifier = Modifier.padding(top = 13.dp)
                )
                Text(
                    text = "A simple and efficient to-do app",
                    color = Color(android.graphics.Color.parseColor("#3991D8"))
                )
            }
        }

        Text(
            text = "Welcome",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Ready to explore? Log in to get started.",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            },
            modifier = Modifier
                .width(280.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD5EDFF)
            ),

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Sign in with Google",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "© UTHSmartTasks",
            color = Color(0xBF000000)
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun notiCard(isLogin: Boolean, email: String){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if(isLogin) Color(0x4D4AABD2) else Color(0x4DEB9797)//4D4AABD2
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = if(isLogin) "Success!" else "Google Sign-In Failed",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 7.dp)
            )
            Text(
                text = if(isLogin) "Hi ${email}" else "User canceled the Google sign-in process.",
                fontWeight = if (isLogin) FontWeight.Bold else FontWeight.Normal,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 7.dp),
                textAlign = TextAlign.Center,
            )
            if (isLogin){
                Text(
                    text = buildAnnotatedString {
                        append("Welcom to ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                            append("UTHSmartTasks")
                        }
                    },
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 7.dp)
                )
            }
        }
    }
}

