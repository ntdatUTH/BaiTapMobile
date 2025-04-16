package com.example.uthsmarttasksmvvm.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface taskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<taskModel>>  // Flow để quan sát dữ liệu thay đổi

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: taskModel)

    @Delete
    suspend fun deleteTask(task: taskModel)
}