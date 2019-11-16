package br.com.mobile2you.m2ybase.data.repositories

import br.com.mobile2you.m2ybase.data.remote.datasources.LoginRemoteDataSource
import br.com.mobile2you.m2ybase.data.remote.models.LoginResponse
import br.com.mobile2you.m2ybase.data.remote.models.LoginRequest
import io.reactivex.Single

object LoginRepository {

    fun signIn(userData: LoginRequest): Single<LoginResponse> {
        return LoginRemoteDataSource.signIn(LoginRequest(
                userData.email,
                userData.password
        ))
    }
}