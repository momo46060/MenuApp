package com.example.myapplication.data.remote.model

import com.example.myapplication.data.local.entity.MenuItemEntity
import kotlinx.serialization.Serializable


@Serializable
data class Item (
    val id :Int,
    val name :String,
    val price : Double,
    val description :String,
    val vegan: Boolean,
    val hot: Boolean,
    val rating: Double,
    val image: String,
    val available: Boolean,
)

fun Item.toEntity(category: String) = MenuItemEntity(
    id = this.id,
    name = this.name,
    price = this.price,
    description = this.description,
    vegan = this.vegan,
    hot = this.hot,
    rating = this.rating,
    image = this.image,
    available = this.available,
    category = category
)