package com.example.userslist.data.remote.dto


import com.example.userslist.domain.model.UserData
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("avatar")
    val avatar: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_name")
    val lastName: String?
)

fun Data.toUserData(): UserData {
    return UserData(
        avatar = avatar,
        email = email,
        firstName = firstName,
        id = id,
        lastName = lastName
    )
}