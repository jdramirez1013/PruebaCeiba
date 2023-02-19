package com.jdr.pruebaceiba.domain

import com.jdr.pruebaceiba.data.repository.UserRepository
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class GetListUsers @Inject constructor(private val repository: UserRepository) {

    suspend fun execute(): List<UserModel> =
        repository.getUsers()

}