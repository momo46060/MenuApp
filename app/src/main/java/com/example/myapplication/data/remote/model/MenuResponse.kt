package com.example.myapplication.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MenuResponse (

    val record: Record,
    val metadata: MetaData
)