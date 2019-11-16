package br.com.joaopaulosj.vanhackathon2019.data.remote.interceptors

import br.com.joaopaulosj.vanhackathon2019.data.local.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddCookieInterceptor : Interceptor {
    private val REQUEST_HEADER_COOKIE = "Cookie"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val id = PreferencesHelper.sessionCookie
        val original = chain.request()
        var request = original

        //Only adds if there's a cookie
        if (!id.isEmpty()) {
            request = original.newBuilder()
                    .addHeader(REQUEST_HEADER_COOKIE, id)
                    .method(original.method(), original.body())
                    .build()
        }

        return chain.proceed(request)
    }

}
