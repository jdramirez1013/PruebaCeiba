package com.jdr.pruebaceiba.data.repository

import com.jdr.pruebaceiba.data.remote.ApiService
import com.jdr.pruebaceiba.model.PostModel
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: ApiService) {

    suspend fun getPosts(userId: Int): List<PostModel> =
        api.getPosts(userId)

}