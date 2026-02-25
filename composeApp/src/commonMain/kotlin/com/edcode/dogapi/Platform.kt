package com.edcode.dogapi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform