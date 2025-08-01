package com.example.myapplication.data.remote.services

import com.example.myapplication.data.remote.model.MenuResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface MenuService {
    @GET("latest")
    suspend fun getMenu(): MenuResponse

}