package com.jdr.pruebaceiba.data.repository

import com.jdr.pruebaceiba.data.local.UserDao
import com.jdr.pruebaceiba.data.remote.ApiService
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: ApiService,
    private val userDao: UserDao
) {

    suspend fun getUsers(): List<UserModel> {

        val localUsers = userDao.getAll()

        if (localUsers.isNotEmpty()) {
            return localUsers
        }

        val response = api.getUsers()

        if (response.isNotEmpty()) {
            userDao.insertAll(response)
        }

        return response
    }

    fun getUser(userId: Int): UserModel? =
        userDao.getUser(userId)

    fun getUsersSearch(query: String): List<UserModel> =
        userDao.getUsersSearch(query)

}