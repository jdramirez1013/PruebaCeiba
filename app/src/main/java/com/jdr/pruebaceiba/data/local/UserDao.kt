package com.jdr.pruebaceiba.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jdr.pruebaceiba.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserModel>)

    @Query("SELECT * FROM user")
    fun getAll(): List<UserModel>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Int): UserModel?

    @Query("SELECT * FROM user WHERE name LIKE '%' || :query || '%'")
    fun getUsersSearch(query: String): List<UserModel>

}