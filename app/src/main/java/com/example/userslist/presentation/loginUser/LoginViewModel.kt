package com.example.userslist.presentation.loginUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.userslist.data.remote.Credential
import com.example.userslist.domain.user_case.login_user.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase) :
    ViewModel() {

    fun loginUser(credential: Credential) =
        loginUserUseCase(credential).asLiveData(viewModelScope.coroutineContext)

}