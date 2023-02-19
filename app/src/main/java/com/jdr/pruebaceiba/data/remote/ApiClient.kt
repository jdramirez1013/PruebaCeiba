package com.jdr.pruebaceiba.data.remote

import com.jdr.pruebaceiba.model.PostModel
import com.jdr.pruebaceiba.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/users")
    suspend fun getUsers(): Response<List<UserModel>>

    @GET("/posts")
    suspend fun getPosts(@Query("userId") userId: Int): Response<List<PostModel>>
}