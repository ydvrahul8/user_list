package com.example.userslist.data.remote

import com.example.userslist.data.remote.dto.AddUserDTO
import com.example.userslist.data.remote.dto.LoginDTO
import com.example.userslist.data.remote.dto.RegisterDTO
import com.example.userslist.data.remote.dto.UsersDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {

    @POST("/api/register")
    suspend fun registerUser(@Body credential: Credential): RegisterDTO

    @POST("/api/login")
    suspend fun loginUser(@Body credential: Credential): LoginDTO

    @GET("/api/users?page=1")
    suspend fun getUsers(): UsersDTO

    @POST("/api/users")
    suspend fun addUser(@Body user: User): AddUserDTO

}