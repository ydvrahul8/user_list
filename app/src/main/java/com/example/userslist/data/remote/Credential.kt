package com.example.userslist.data.remote

import com.google.gson.annotations.SerializedName

data class Credential(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)