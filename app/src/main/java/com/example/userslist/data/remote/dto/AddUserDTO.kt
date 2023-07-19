package com.example.userslist.data.remote.dto


import com.example.userslist.domain.model.AddUserData
import com.google.gson.annotations.SerializedName

data class AddUserDTO(
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("job")
    val job: String?,
    @SerializedName("name")
    val name: String?
)

fun AddUserDTO.toAddUserData(): AddUserData {
    return AddUserData(
        id = id,
        job = job,
        name = name
    )
}