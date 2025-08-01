package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.entity.MenuItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<MenuItemEntity>)

    @Query("SELECT * FROM menu_items WHERE category = :category")
    fun getMenuItemsByCategory(category: String): List<MenuItemEntity>

    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems(): List<MenuItemEntity>

    @Query("DELETE FROM menu_items")
    suspend fun clearAll()
}