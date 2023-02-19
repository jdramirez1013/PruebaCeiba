package com.jdr.pruebaceiba.data.repository

import com.jdr.pruebaceiba.data.local.UserDao
import com.jdr.pruebaceiba.data.remote.ApiService
import com.jdr.pruebaceiba.model.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: ApiService, private val userDao: UserDao) {

    suspend fun getUsers(): List<UserModel>{

        val localList = userDao.getAll()

        if(localList.isNotEmpty()){
            return  localList
        }

        val response = api.getUsers()

        response.forEach {
            userDao.insertUser(it)
        }

        return response
    }

    suspend fun getUser(userId: Int): UserModel =
        userDao.getUser(userId)

}