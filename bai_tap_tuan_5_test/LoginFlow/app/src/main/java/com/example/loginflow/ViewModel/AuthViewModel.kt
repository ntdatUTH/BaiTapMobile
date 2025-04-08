package com.example.loginflow.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflow.AuthenticationRepository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(private val context: Context) : ViewModel() {

    val repository = AuthRepository(FirebaseAuth.getInstance(), context)

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> = _user

    private val _userName = MutableStateFlow<String>("")
    val userName: StateFlow<String> = _userName

    private val _userEmail = MutableStateFlow<String>("")
    val userEmail: StateFlow<String> = _userEmail

    private val _userBirthday = MutableStateFlow<String>("")
    val userBirthday: StateFlow<String> = _userBirthday

    fun signInWithGoogle(idToken: String, account: GoogleSignInAccount) {
        viewModelScope.launch(Dispatchers.IO) {  // 🔥 Sử dụng Dispatchers.IO để chạy trên luồng nền
            try {
                val firebaseUser = repository.firebaseAuthWithGoogle(idToken)
                withContext(Dispatchers.Main) {  // Chuyển về Main Thread để cập nhật giao diện
                    _user.value = firebaseUser
                    firebaseUser?.let {
                        _userName.value = it.displayName ?: "No Name"
                        _userEmail.value = it.email ?: "No Email"

                        // Gọi API lấy ngày sinh (nếu cần thiết)
                        val birthday = repository.getUserBirthday(account)
                        _userBirthday.value = birthday ?: "No Birthday Found"
                    }
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error in signInWithGoogle: ${e.message}")
            }
        }
    }

    fun loadCurrentUser() {
        val currentUser = repository.getCurrentUser()
        _user.value = currentUser
        currentUser?.let {
            _userName.value = it.displayName ?: "No Name"
            _userEmail.value = it.email ?: "No Email"
        }
    }

}
