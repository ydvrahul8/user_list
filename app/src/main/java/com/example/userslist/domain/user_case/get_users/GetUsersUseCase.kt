package com.example.userslist.domain.user_case.get_users

import com.example.userslist.common.Resource
import com.example.userslist.data.remote.dto.toUserData
import com.example.userslist.domain.model.UserData
import com.example.userslist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(): Flow<Resource<List<UserData>>> = flow {
        try {
            emit(Resource.Loading())
            val userResponse = repository.getUsers().data.map { it.toUserData() }
            emit(Resource.Success<List<UserData>>(userResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<UserData>>("Couldn't reach server. Check your internet connection."))
        }
    }
}