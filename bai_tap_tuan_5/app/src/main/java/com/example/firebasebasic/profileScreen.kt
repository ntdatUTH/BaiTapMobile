package com.example.firebasebasic

import android.app.DatePickerDialog
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebasebasic.MainActivity.Companion.signOut
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

@Composable
fun profileScreen(navController: NavController, userName: String, userEmail: String, googleSignInClient: GoogleSignInClient, loginViewModel: LoginViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 20.dp)
    ) {
        topLayout(navController, googleSignInClient, loginViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uth), // Ảnh từ drawable
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp) // Đặt kích thước ảnh
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .offset(x = 55.dp, y = 45.dp) // Di chuyển toàn bộ IconButton
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera), // Ảnh từ drawable
                    contentDescription = "icon",
                    modifier = Modifier
                        .size(27.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Black, CircleShape)
                        .background(Color.White)
                )
            }
        }
        inforItem("name",userName ,false)
        inforItem("email",userEmail ,false)
        inforItem("birth","" ,true)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview (showBackground = true)
@Composable
fun topLayout(navController: NavController, googleSignInClient: GoogleSignInClient, loginViewModel: LoginViewModel){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        CompositionLocalProvider(
            LocalMinimumTouchTargetEnforcement provides false,
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                    signOut(googleSignInClient, loginViewModel)
                          },
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
        Spacer(modifier = Modifier.width(135.dp))
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color(android.graphics.Color.parseColor("#2196F3"))
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun inforItem(title: String, user: String, editInfor: Boolean){
    Row(
        modifier = Modifier.padding(top = 20.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(

            imageVector = when(title){
                "name"-> Icons.Default.AccountCircle
                "email"-> Icons.Default.Email
                "birth"-> Icons.Default.Lock
                else -> Icons.Default.AccountCircle
            },
            contentDescription = "icon",
        )
        Text(
            text = when(title){
                "name"-> "Name"
                "email"-> "Email"
                "birth"-> "Date of Birth"
                else -> ""
            },
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
    var inforName by remember { mutableStateOf(when(title){
        "name"-> user
        "email"-> user
        "birth"-> "dd/mm/yyyy"
        else -> ""
    }) }

    if(title == "birth"){
        AutoFormatDateField()
    }else{
        TextField(
            value = inforName,
            onValueChange = {inforName = it},
            enabled = editInfor,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(android.graphics.Color.parseColor("#E0E0E0")), // Màu nền khi bị disabled
                disabledIndicatorColor = Color.Transparent // Ẩn viền khi bị disabled

            ),
            textStyle = TextStyle(fontSize = 16.sp),
            shape = RoundedCornerShape(12.dp), // Bo góc
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoFormatDateField() {
    var dateInput by remember { mutableStateOf(TextFieldValue("")) }
    var isDeleting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        OutlinedTextField(
            value = dateInput,
            onValueChange = { newValue ->
                val oldText = dateInput.text
                val newText = newValue.text

                isDeleting = oldText.length > newText.length

                val digitsOnly = newText.filter { it.isDigit() }.take(8)
                val formattedText = buildString {
                    for (i in digitsOnly.indices) {
                        append(digitsOnly[i])
                        if (!isDeleting && (i == 1 || i == 3) && i != digitsOnly.lastIndex) {
                            append("/")
                        }
                    }
                }
                dateInput = TextFieldValue(
                    text = formattedText,
                    selection = TextRange(formattedText.length)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            placeholder = { Text("dd/mm/yyyy") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(android.graphics.Color.parseColor("#E0E0E0")), // Màu nền khi bị disabled
                unfocusedBorderColor = Color.Transparent, // Ẩn viền khi không được chọn
                ),
            textStyle = TextStyle(fontSize = 16.sp),
            shape = RoundedCornerShape(12.dp), // Bo góc

            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
