package com.example.userslist.presentation.registerUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.userslist.data.remote.Credential
import com.example.userslist.domain.user_case.register_user.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUserUseCase: RegisterUserUseCase) :
    ViewModel() {
    fun registerUser(credential: Credential) =
        registerUserUseCase(credential).asLiveData(viewModelScope.coroutineContext)
}