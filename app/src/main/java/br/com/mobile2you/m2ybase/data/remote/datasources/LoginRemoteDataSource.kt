package br.com.mobile2you.m2ybase.data.remote.datasources

import br.com.mobile2you.m2ybase.NetworkConstants.LOGIN_URL
import br.com.mobile2you.m2ybase.data.remote.ServiceGenerator
import br.com.mobile2you.m2ybase.data.remote.interceptors.AddCookieInterceptor
import br.com.mobile2you.m2ybase.data.remote.interceptors.ReceivedCookieInterceptor
import br.com.mobile2you.m2ybase.data.remote.models.LoginRequest
import br.com.mobile2you.m2ybase.data.remote.services.LoginService

object LoginRemoteDataSource {

    private val service = ServiceGenerator.createCommonService(LoginService::class.java,
            listOf(ReceivedCookieInterceptor(), AddCookieInterceptor()), LOGIN_URL)

    fun signIn(signInRequest: LoginRequest) = service.signIn(signInRequest)
}