package com.example.flowapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Data Class for the root of the JSON response
data class TaskResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

// Data Class for each task
@Parcelize
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val status: String ,
    val priority: String,
    val category: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String,
    val subtasks: List<Subtask>,
    val attachments: List<Attachment>,
    val reminders: List<Reminder>
) : Parcelable

// Data Class for subtasks
@Parcelize
data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean
) : Parcelable

// Data Class for attachments
@Parcelize
data class Attachment(
    val id: Int,
    val fileName: String,
    val fileUrl: String
) : Parcelable

// Data Class for reminders
@Parcelize
data class Reminder(
    val id: Int,
    val time: String,
    val type: String
) : Parcelable