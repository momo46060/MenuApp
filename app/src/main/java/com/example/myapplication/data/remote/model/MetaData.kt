package com.example.myapplication.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    val id: String,
    val private: Boolean,
    val createdAt: String
)
