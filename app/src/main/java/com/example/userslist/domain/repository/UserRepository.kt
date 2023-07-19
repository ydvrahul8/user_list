package com.example.userslist.domain.repository

import com.example.userslist.data.remote.Credential
import com.example.userslist.data.remote.User
import com.example.userslist.data.remote.dto.AddUserDTO
import com.example.userslist.data.remote.dto.LoginDTO
import com.example.userslist.data.remote.dto.RegisterDTO
import com.example.userslist.data.remote.dto.UsersDTO

interface UserRepository {
    suspend fun loginUser(credential: Credential): LoginDTO
    suspend fun registerUser(credential: Credential): RegisterDTO
    suspend fun getUsers(): UsersDTO
    suspend fun addUser(user: User): AddUserDTO
}