package com.example.uthsmarttasksmvvm.ViewModel

import android.content.ClipData.Item
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.uthsmarttasksmvvm.Model.taskModel

class taskViewModel: ViewModel() {
    private var nextId = 1
    var taskList = mutableStateListOf<taskModel>()
        private set
    fun addTask(task: String, desc: String){
        taskList.add(taskModel(id = nextId++, task, desc))
    }
}