package com.edcode.dogapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreadsDogs (

    @SerialName("message" ) var message : Map<String, List<String>>? = null,
    @SerialName("status"  ) var status  : String?  = null

)