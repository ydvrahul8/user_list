package com.example.userslist.presentation.showUsers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.userslist.domain.user_case.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowUsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase):ViewModel() {
    fun getUser() = getUsersUseCase().asLiveData(viewModelScope.coroutineContext)
}