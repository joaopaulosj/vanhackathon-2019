package br.com.joaopaulosj.vanhackathon2019.data.remote.datasources

import br.com.joaopaulosj.vanhackathon2019.NetworkConstants.LOGIN_URL
import br.com.joaopaulosj.vanhackathon2019.data.remote.ServiceGenerator
import br.com.joaopaulosj.vanhackathon2019.data.remote.interceptors.AddCookieInterceptor
import br.com.joaopaulosj.vanhackathon2019.data.remote.interceptors.ReceivedCookieInterceptor
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.LoginRequest
import br.com.joaopaulosj.vanhackathon2019.data.remote.services.LoginService

object LoginRemoteDataSource {

    private val service = ServiceGenerator.createCommonService(LoginService::class.java,
            listOf(ReceivedCookieInterceptor(), AddCookieInterceptor()), LOGIN_URL)

    fun signIn(signInRequest: LoginRequest) = service.signIn(signInRequest)
}