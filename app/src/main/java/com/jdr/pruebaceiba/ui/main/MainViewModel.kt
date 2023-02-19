package com.jdr.pruebaceiba.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdr.pruebaceiba.domain.GetListUsers
import com.jdr.pruebaceiba.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getListUsers: GetListUsers): ViewModel() {
    var listUser = MutableLiveData<List<UserModel>>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        getUsers()
    }

    private fun getUsers() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = getListUsers.execute()
            viewModelScope.launch(Dispatchers.Main) {
                listUser.value = response
                isLoading.value = false
            }
        }
    }

}