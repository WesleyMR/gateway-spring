package com.celia.gateway.interfaces.dto

data class MeResponse(
    val id: String,
    val username: String,
    val refreshToken: String? = null
)
