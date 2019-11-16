package br.com.joaopaulosj.vanhackathon2019.data.remote.services

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("signin")
    fun signIn(@Body signInRequest: LoginRequest): Single<LoginResponse>
}