package com.example.userslist.presentation.addUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.userslist.data.remote.User
import com.example.userslist.domain.user_case.add_user.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) :
    ViewModel() {

    fun addUser(user: User) = addUserUseCase(user).asLiveData(viewModelScope.coroutineContext)
}