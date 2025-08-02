package com.example.myapplication.data.repository

import android.content.Context
import android.util.Log
import androidx.core.view.forEach
import com.example.myapplication.data.local.MenuDao
import com.example.myapplication.data.local.entity.MenuItemEntity
import com.example.myapplication.data.remote.model.Menu
import com.example.myapplication.data.remote.model.toEntity
import com.example.myapplication.data.remote.services.MenuService
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.repository.MenuRepository
import com.example.myapplication.utils.hasInternet
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
   private val service: MenuService,
   private val menuDao: MenuDao,
    @ApplicationContext private val context: Context
) : MenuRepository {
    override suspend fun getMenu(): Flow<Resource<List<MenuItemEntity>>> = flow {
        emit(Resource.Loading)

        if (hasInternet(context)){
            try {
                val response = service.getMenu()
                if (response.isSuccessful && response.body() != null) {
                    val entities = mapApiResponseToEntities(response.body()!!.record.menu)
                    menuDao.clearAll()
                    menuDao.insertAll(entities)
                    emit(Resource.Success(entities))
                    mapApiResponseToEntities(response.body()!!.record.menu)
                } else {
                    emit(Resource.Error("Failed to fetch menu"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occurred"))
            }
        }else{
            val cached = menuDao.getAllMenuItems()
            if (cached.isNotEmpty()) {
                emit(Resource.Success(cached))
            } else {
                emit(Resource.Error("No internet and no cached data"))
            }
        }

    }

    fun mapApiResponseToEntities(menu: Menu): List<MenuItemEntity> {
        val items = mutableListOf<MenuItemEntity>()
        menu.hamburgers.forEach {
            items.add(it.toEntity("hamburgers"))
        }
        menu.pasta.forEach {
            items.add(it.toEntity("pasta"))
        }
        menu.drinks.forEach {
            items.add(it.toEntity("drinks"))
        }
        menu.sandwiches.forEach {
            items.add(it.toEntity("sandwiches"))
        }

        return items
    }


}