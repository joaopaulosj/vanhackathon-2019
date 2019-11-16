package br.com.mobile2you.m2ybase.data.remote.interceptors

import br.com.mobile2you.m2ybase.data.local.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val RESPONSE_HEADER_COOKIE = "Set-Cookie"

class ReceivedCookieInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        val cookie = originalResponse.headers().get(RESPONSE_HEADER_COOKIE)
        try {
            if (!cookie!!.isEmpty() && PreferencesHelper.sessionCookie != cookie) {
                PreferencesHelper.sessionCookie = cookie
            }
        } catch (e: NullPointerException) {
            //Do Nothing
        }

        return originalResponse
    }

}
