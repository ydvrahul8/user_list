package com.example.userslist.domain.user_case.add_user

import com.example.userslist.common.Resource
import com.example.userslist.data.remote.User
import com.example.userslist.data.remote.dto.toAddUserData
import com.example.userslist.domain.model.AddUserData
import com.example.userslist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(user: User): Flow<Resource<AddUserData>> = flow {
        try {
            emit(Resource.Loading())
            val addUserResponse = repository.addUser(user).toAddUserData()
            emit(Resource.Success<AddUserData>(addUserResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<AddUserData>("Couldn't reach server. Check your internet connection."))
        }
    }
}