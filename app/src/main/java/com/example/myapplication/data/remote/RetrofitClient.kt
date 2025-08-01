package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.services.MenuService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object RetrofitClient {
    val BASE_URL=
        "https://api.jsonbin.io/v3/b/6882874df7e7a370d1ed6cc1/"
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Options: NONE, BASIC, HEADERS, BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Master-Key", "\$2a\$10\$gKGk.WhhPDqDbokEJkqkUucnE66bIZSSTGM31bo6SUkYjYbRD2Hf6")
                .build()
            chain.proceed(request)
        }
        .build()

    val instance: MenuService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MenuService::class.java)
    }


}