package com.example.uthsmarttasksmvvm.ViewModel

import com.example.uthsmarttasksmvvm.Model.taskDao
import com.example.uthsmarttasksmvvm.Model.taskModel
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val userDao: taskDao) {
    val allUsers: Flow<List<taskModel>> = userDao.getAllTasks()

    suspend fun insert(task: taskModel) {
        userDao.insertTask(task)
    }

    suspend fun delete(user: taskModel) {
        userDao.deleteTask(user)
    }
}