package com.example.userslist.data.remote


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("job")
    val job: String?,
    @SerializedName("name")
    val name: String?
)