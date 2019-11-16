package br.com.mobile2you.m2ybase.data.remote.services

import br.com.mobile2you.m2ybase.data.remote.models.LoginResponse
import br.com.mobile2you.m2ybase.data.remote.models.LoginRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("signin")
    fun signIn(@Body signInRequest: LoginRequest): Single<LoginResponse>
}