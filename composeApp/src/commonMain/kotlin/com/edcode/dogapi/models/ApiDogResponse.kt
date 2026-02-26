package com.edcode.dogapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiDogResponse(
    val status:String,
    @SerialName("message")
    val ok:String
)