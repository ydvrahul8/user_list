package com.example.userslist.domain.user_case.login_user

import com.example.userslist.common.Resource
import com.example.userslist.data.remote.Credential
import com.example.userslist.data.remote.dto.toLoginData
import com.example.userslist.domain.model.LoginUserData
import com.example.userslist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val repository: UserRepository  ) {

    operator fun invoke(credential: Credential): Flow<Resource<LoginUserData>> = flow {
        try {
            emit(Resource.Loading())
            val loginResponse = repository.loginUser(credential).toLoginData()
            emit(Resource.Success<LoginUserData>(loginResponse))
        }catch ( e:HttpException){
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<LoginUserData>("Couldn't reach server. Check your internet connection."))
        }
    }
}