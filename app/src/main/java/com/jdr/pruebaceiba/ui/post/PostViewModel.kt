package com.jdr.pruebaceiba.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdr.pruebaceiba.domain.GetListPost
import com.jdr.pruebaceiba.domain.GetUser
import com.jdr.pruebaceiba.model.PostModel
import com.jdr.pruebaceiba.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getUser: GetUser,
    private val getListPost: GetListPost
) : ViewModel() {
    var listPost = MutableLiveData<List<PostModel>>()
    var user = MutableLiveData<UserModel>()
    var isLoading = MutableLiveData<Boolean>()

    fun getUserAndPostById(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUser(userId)
            if (response != null) {
                viewModelScope.launch(Dispatchers.Main) {
                    user.value = response!!
                }
            }
        }
        getPosts(userId)
    }

    private fun getPosts(userId: Int) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = getListPost(userId)
            viewModelScope.launch(Dispatchers.Main) {
                listPost.value = response
                isLoading.value = false
            }
        }
    }
}