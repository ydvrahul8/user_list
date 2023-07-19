package com.example.userslist.domain.user_case.register_user

import com.example.userslist.common.Resource
import com.example.userslist.data.remote.Credential
import com.example.userslist.data.remote.dto.toRegisterData
import com.example.userslist.domain.model.RegisterUserData
import com.example.userslist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(credential: Credential): Flow<Resource<RegisterUserData>> = flow {
        try {
            emit(Resource.Loading())
            val registerResponse = repository.registerUser(credential).toRegisterData()
            emit(Resource.Success<RegisterUserData>(registerResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<RegisterUserData>("Couldn't reach server. Check your internet connection."))
        }
    }
}