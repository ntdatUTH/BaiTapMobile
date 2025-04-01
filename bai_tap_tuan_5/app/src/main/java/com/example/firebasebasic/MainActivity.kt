package com.example.firebasebasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val loginViewModel: LoginViewModel by viewModels() // Khởi tạo ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Lấy từ google-services.json
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            routeScreen(googleSignInClient, loginViewModel)
//            profileScreen()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    loginViewModel.setLoginState(true, user?.email ?: "", user?.displayName?:"")
                    Log.d("GoogleSignIn", "Đăng nhập thành công: ${user?.displayName}")
                } else {
                    Log.e("GoogleSignIn", "Xác thực thất bại")
                }
            }
    }
    companion object {
        fun signOut(googleSignInClient: GoogleSignInClient, loginViewModel: LoginViewModel) {
            googleSignInClient.signOut().addOnCompleteListener {
                FirebaseAuth.getInstance().signOut()
                loginViewModel.setLoginState(false)
                Log.d("GoogleSignIn", "Đã đăng xuất thành công")
            }
        }
    }
}
