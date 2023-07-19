package com.example.userslist.data.remote.dto


import com.example.userslist.domain.model.LoginUserData
import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("token")
    val token: String?
)

fun LoginDTO.toLoginData(): LoginUserData {
    return LoginUserData(token = token)
}