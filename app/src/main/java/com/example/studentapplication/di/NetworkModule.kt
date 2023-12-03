package com.example.studentapplication.di

import com.example.studentapplication.data.remote.ApiService
import com.example.studentapplication.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        val loginInterceptor = HttpLoggingInterceptor()
        loginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
            )
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .connectionSpecs(Collections.singletonList(spec))
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(80, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build()

                val hasMultipart: Boolean =
                    requestBuilder.headers.names().contains("multipart")

                loginInterceptor.setLevel(if (hasMultipart) HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY)


                chain.proceed(requestBuilder)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}