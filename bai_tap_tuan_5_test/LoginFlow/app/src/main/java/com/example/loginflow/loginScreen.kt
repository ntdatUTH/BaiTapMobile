package com.example.loginflow

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginflow.ViewModel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GoogleSignInScreen(
    authViewModel: AuthViewModel,
    activity: Activity
) {
    val isSignedIn by authViewModel.user.collectAsState()
    val userName by authViewModel.userName.collectAsState()
    val userEmail by authViewModel.userEmail.collectAsState()
    val userBirthday by authViewModel.userBirthday.collectAsState() // 🔥 Thêm trạng thái ngày sinh

    val googleSignInClient = remember { authViewModel.repository.getGoogleSignInClient(activity) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val idToken = account.idToken
                if (idToken != null) {
                    authViewModel.signInWithGoogle(idToken, account) // 🔥 Truyền cả `account`
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Sign in failed: ${e.statusCode}")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isSignedIn != null) {
            Text(text = "Đăng nhập thành công!")
            Text(text = "Tên: $userName")
            Text(text = "Email: $userEmail")
            Text(text = "🎂 Ngày sinh: $userBirthday") // 🔥 Hiển thị ngày sinh
        } else {
            Button(onClick = {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }) {
                Text("Đăng nhập bằng Google")
            }
        }
    }
}
