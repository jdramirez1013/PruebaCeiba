package com.jdr.pruebaceiba.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdr.pruebaceiba.domain.GetListUsers
import com.jdr.pruebaceiba.domain.GetUsersSearch
import com.jdr.pruebaceiba.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getListUsers: GetListUsers,
    private val getUsersSearch: GetUsersSearch
) : ViewModel() {
    var listUser = MutableLiveData<List<UserModel>>()
    var isLoading = MutableLiveData<Boolean>()
    var emptyList = MutableLiveData<Boolean>()

    init {
        getUsers()
    }

    private fun getUsers() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = getListUsers()
            viewModelScope.launch(Dispatchers.Main) {
                listUser.value = response
                isLoading.value = false
            }
        }
    }

    fun getUsersBySearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUsersSearch(query)
            viewModelScope.launch(Dispatchers.Main) {
                emptyList.value = response.isEmpty()
                listUser.value = response
            }
        }
    }

}