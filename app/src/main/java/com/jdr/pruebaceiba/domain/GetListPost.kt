package com.jdr.pruebaceiba.domain

import com.jdr.pruebaceiba.data.repository.PostRepository
import com.jdr.pruebaceiba.model.PostModel
import javax.inject.Inject

class GetListPost @Inject constructor(private val repository: PostRepository) {

    suspend fun execute(userId: Int): List<PostModel> =
        repository.getPosts(userId)

}