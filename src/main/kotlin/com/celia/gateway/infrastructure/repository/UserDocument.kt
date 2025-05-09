package com.celia.gateway.infrastructure.repository

import com.celia.gateway.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class UserDocument(
    @Id val id: String? = null,
    val username: String,
    val passwordHash: String,
    val refreshToken: String? = null //
) {
    fun toDomain() = User(id, username, passwordHash)
}