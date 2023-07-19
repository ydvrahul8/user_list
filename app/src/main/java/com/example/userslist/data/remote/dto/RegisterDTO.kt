package com.example.userslist.data.remote.dto


import com.example.userslist.domain.model.RegisterUserData
import com.google.gson.annotations.SerializedName

data class RegisterDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("token")
    val token: String?
)

fun RegisterDTO.toRegisterData(): RegisterUserData {
    return RegisterUserData(
        id = id,
        token = token,
    )
}