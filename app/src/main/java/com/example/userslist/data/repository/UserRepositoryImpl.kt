package com.example.userslist.data.repository

import com.example.userslist.data.remote.Credential
import com.example.userslist.data.remote.User
import com.example.userslist.data.remote.UserAPI
import com.example.userslist.data.remote.dto.AddUserDTO
import com.example.userslist.data.remote.dto.LoginDTO
import com.example.userslist.data.remote.dto.RegisterDTO
import com.example.userslist.data.remote.dto.UsersDTO
import com.example.userslist.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userAPI: UserAPI) : UserRepository {
    override suspend fun loginUser(credential: Credential): LoginDTO {
        return userAPI.loginUser(credential)
    }

    override suspend fun registerUser(credential: Credential): RegisterDTO {
        return userAPI.registerUser(credential)
    }

    override suspend fun getUsers(): UsersDTO {
        return userAPI.getUsers()
    }

    override suspend fun addUser(user: User): AddUserDTO {
        return userAPI.addUser(user )
    }
}