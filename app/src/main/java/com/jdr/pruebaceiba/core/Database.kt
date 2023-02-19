package com.jdr.pruebaceiba.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jdr.pruebaceiba.data.local.UserDao
import com.jdr.pruebaceiba.model.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun userDao() : UserDao
}