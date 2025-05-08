package com.celia.gateway.domain.model

data class User(
    val id: String? = null,
    val username: String,
    val passwordHash: String
)