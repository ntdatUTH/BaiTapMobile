package com.example.uthsmarttasksmvvm.ViewModel

import android.app.Application
import android.content.ClipData.Item
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasksmvvm.Model.AppDatabase
import com.example.uthsmarttasksmvvm.Model.taskModel
import kotlinx.coroutines.launch

//class taskViewModel: ViewModel() {
//    private var nextId = 1
//    var taskList = mutableStateListOf<taskModel>()
//        private set
//    fun addTask(task: String, desc: String){
//        taskList.add(taskModel(id = nextId++, task, desc))
//    }
//    fun checkData(task: String, desc: String, context: Context): Boolean{
//        if (task.isNotBlank() && desc.isNotBlank()){
//            addTask(task, desc)
//            Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show()
//            return true
//        }else{
//            Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show()
//            return false
//        }
//    }
//}

class taskViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val repository = TaskRepository(userDao)
    private var nextId = 1
    val tasks: LiveData<List<taskModel>> = repository.allUsers.asLiveData()

    fun addTask(task: String, desc: String) {
        viewModelScope.launch {
            repository.insert(taskModel(id = nextId++,task = task, desc = desc))
        }
    }
    fun checkData(task: String, desc: String, context: Context): Boolean{
        if (task.isNotBlank() && desc.isNotBlank()){
            addTask(task, desc)
            Toast.makeText(context, "Thêm mới thành công", Toast.LENGTH_SHORT).show()
            return true
        }else{
            Toast.makeText(context, "Thêm mới thất bại", Toast.LENGTH_SHORT).show()
            return false
        }
    }
    fun deleteTask(task: taskModel) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}

