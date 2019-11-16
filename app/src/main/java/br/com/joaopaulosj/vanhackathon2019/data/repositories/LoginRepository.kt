package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.datasources.LoginRemoteDataSource
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginRequest
import io.reactivex.Single

object LoginRepository {

    fun signIn(userData: LoginRequest): Single<LoginResponse> {
        return LoginRemoteDataSource.signIn(LoginRequest(
                userData.email,
                userData.password
        ))
    }
}