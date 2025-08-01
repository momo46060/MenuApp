package com.example.myapplication.domain.repository

import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getMenu(): Flow<Resource<List<MenuItemEntity>>>
}
