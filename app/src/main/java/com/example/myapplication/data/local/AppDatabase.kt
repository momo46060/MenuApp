package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.entity.MenuItemEntity

@Database(entities = [MenuItemEntity::class], version = 1)
abstract  class AppDatabase: RoomDatabase() {

    abstract fun menuDao(): MenuDao
}