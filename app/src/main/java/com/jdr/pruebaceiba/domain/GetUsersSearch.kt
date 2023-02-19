package com.jdr.pruebaceiba.domain

import com.jdr.pruebaceiba.data.repository.UserRepository
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class GetUsersSearch @Inject constructor(private val repository: UserRepository) {
    suspend fun execute(query: String): List<UserModel> =
        repository.getUserSearch(query)
}