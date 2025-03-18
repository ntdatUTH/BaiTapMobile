package com.example.bai_tap_2_1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button_check: Button = findViewById(R.id.buttonCheck)
        button_check.setOnClickListener {
            val edit_age = findViewById<EditText>(R.id.editTextAge)
            val age = (edit_age.text.toString()).toInt()
            var thongbao = findViewById<TextView>(R.id.textThongbao)
            var message: String =""
            when{
                age>65 -> message="Người già"
                age in 6..65 -> message="Người lớn"
                age in 2..5 -> message="Trẻ em"
                else -> message="Em bé"
            }
            thongbao.text=message
        }
    }
}