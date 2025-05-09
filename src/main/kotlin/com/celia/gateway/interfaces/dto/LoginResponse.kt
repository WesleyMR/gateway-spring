package com.celia.gateway.interfaces.dto

data class LoginResponse(
    val status: Int,
    val accessToken: String,
    val refreshToken: String
)