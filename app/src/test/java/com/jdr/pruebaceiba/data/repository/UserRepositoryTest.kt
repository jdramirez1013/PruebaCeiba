package com.jdr.pruebaceiba.data.repository

import com.jdr.pruebaceiba.data.local.UserDao
import com.jdr.pruebaceiba.data.remote.ApiService
import com.jdr.pruebaceiba.model.UserModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class UserRepositoryTest {

    @RelaxedMockK
    private lateinit var apiService: ApiService

    @RelaxedMockK
    private lateinit var userDao: UserDao

    lateinit var userRepository: UserRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        userRepository = UserRepository(apiService, userDao)
    }

    @Test
    fun `when database is empty return data of api`() = runBlocking {
        //Given
        coEvery { userDao.getAll() } returns emptyList()
        val users =
            listOf(UserModel(id = 1, name = "Name", phone = "+00000", email = "email@email.com"))
        coEvery { apiService.getUsers() } returns users

        //When
        val response = userRepository.getUsers()

        //Then
        coVerify(exactly = 1) { userDao.getAll() }
        coVerify(exactly = 1) { apiService.getUsers() }
        coVerify(exactly = 1) { userDao.insertAll(any()) }

        assert(response == users)
    }

    @Test
    fun `when database return something return value` () = runBlocking {
        //Given
        val users =
            listOf(UserModel(id = 1, name = "Name", phone = "+00000", email = "email@email.com"))
        coEvery { userDao.getAll() } returns users

        //When
        val response = userRepository.getUsers()

        //Then
        coVerify(exactly = 1) { userDao.getAll() }
        coVerify(exactly = 0) { apiService.getUsers() }
        coVerify(exactly = 0) { userDao.insertAll(any()) }

        assert(response == users)
    }

    @Test
    fun `when database is empty and api failure` () = runBlocking {
        //Given
        coEvery { userDao.getAll() } returns emptyList()
        coEvery { apiService.getUsers() } returns emptyList()

        //When
        val response = userRepository.getUsers()

        //Then
        coVerify(exactly = 1) { userDao.getAll() }
        coVerify(exactly = 1) { apiService.getUsers() }
        coVerify(exactly = 0) { userDao.insertAll(any()) }

        assert(response.isEmpty())
    }

    @Test
    fun `when found a user by id in database` () = runBlocking {
        //Given
        val user = UserModel(id = 1, name = "Name", phone = "+00000", email = "email@email.com")
        coEvery { userDao.getUser(1) } returns user

        //When
        val response = userRepository.getUser(1)

        //Then
        coVerify(exactly = 1) { userDao.getUser(any()) }
        assert(response == user)
    }

    @Test
    fun `when not found a user by id in database` () = runBlocking {
        //Given
        coEvery { userRepository.getUser(any()) } returns null

        //When
        val response = userRepository.getUser(1)

        //Then
        coVerify(exactly = 1) { userDao.getUser(any()) }
        assert(response == null)
    }

}