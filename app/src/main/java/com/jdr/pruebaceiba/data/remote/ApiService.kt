package com.jdr.pruebaceiba.data.remote

import com.jdr.pruebaceiba.model.PostModel
import com.jdr.pruebaceiba.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getUsers(): List<UserModel> {
        return withContext(Dispatchers.IO) {
            val response = apiClient.getUsers()
            response.body() ?: listOf()
        }
    }

    suspend fun getPosts(userId: Int): List<PostModel>{
        return withContext(Dispatchers.IO) {
            val response = apiClient.getPosts(userId)
            response.body() ?: arrayListOf()
        }
    }
}