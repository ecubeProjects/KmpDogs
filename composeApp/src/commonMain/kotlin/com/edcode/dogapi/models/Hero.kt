package com.edcode.dogapi.models

import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    val id:String,
    val name:String
)

