package com.example.uthsmarttasksmvvm.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class taskModel (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var task: String,
    var desc: String,
)