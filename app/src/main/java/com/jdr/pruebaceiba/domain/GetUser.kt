package com.jdr.pruebaceiba.domain

import com.jdr.pruebaceiba.data.repository.UserRepository
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class GetUser @Inject constructor(private val repository: UserRepository){

    suspend fun execute(userId: Int): UserModel =
        repository.getUser(userId)

}