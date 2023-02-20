package com.jdr.pruebaceiba.domain

import com.jdr.pruebaceiba.data.repository.UserRepository
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class GetUsersSearch @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(query: String): List<UserModel> =
        repository.getUsersSearch(query)
}