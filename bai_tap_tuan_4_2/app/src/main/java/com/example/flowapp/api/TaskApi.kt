package com.example.flowapp.api

import com.example.flowapp.model.TaskResponse
import retrofit2.Response
import retrofit2.http.GET

interface TaskApi {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): Response<TaskResponse> // Updated return type
}