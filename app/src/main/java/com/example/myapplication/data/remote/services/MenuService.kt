package com.example.myapplication.data.remote.services

import com.example.myapplication.data.remote.model.MenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface MenuService {
    @GET("latest")
    suspend fun getMenu(): Response<MenuResponse>

}