package com.example.myapplication.data.remote.model

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