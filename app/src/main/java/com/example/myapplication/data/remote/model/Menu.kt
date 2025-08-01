package com.example.myapplication.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val hamburgers: List<Item>,
    val pasta: List<Item>,
    val drinks: List<Item>,
    val sandwiches: List<Item>,
)