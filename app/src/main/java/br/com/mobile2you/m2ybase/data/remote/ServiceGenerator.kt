package br.com.mobile2you.m2ybase.data.remote

import br.com.mobile2you.m2ybase.BuildConfig
import br.com.mobile2you.m2ybase.NetworkConstants
import br.com.mobile2you.m2ybase.data.remote.interceptors.AddCookieInterceptor
import br.com.mobile2you.m2ybase.data.remote.interceptors.ReceivedCookieInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        /**
         * Creates generic service
         */
        fun <S> createService(serviceClass: Class<S>, interceptors: List<Interceptor>? = null, url: String): S {
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(HttpLoggingInterceptor()
                    .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))

            interceptors?.let {
                for (interceptor in interceptors) {
                    httpClient.addInterceptor(interceptor)
                }
            }
            retrofit.client(httpClient.build())
            return retrofit.build().create(serviceClass)
        }

        /**
         * Creates service for a common M2Y API
         */
        fun <S> createCommonService(serviceClass: Class<S>, interceptors: List<Interceptor>?, url: String?): S {
            val retrofit = Retrofit.Builder()
                    .baseUrl(url ?: NetworkConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(ReceivedCookieInterceptor())
                    .addInterceptor(AddCookieInterceptor())
                    .addInterceptor(HttpLoggingInterceptor()
                            .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))


            interceptors?.let {
                for (interceptor in interceptors) {
                    httpClient.addInterceptor(interceptor)
                }
            }

            retrofit.client(httpClient.build())
            return retrofit.build().create(serviceClass)
        }
    }

}