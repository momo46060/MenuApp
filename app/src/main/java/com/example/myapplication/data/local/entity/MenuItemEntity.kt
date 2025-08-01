package com.example.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val description: String,
    val vegan: Boolean,
    val hot: Boolean,
    val rating: Double,
    val image: String,
    val available: Boolean,
    val category: String
)