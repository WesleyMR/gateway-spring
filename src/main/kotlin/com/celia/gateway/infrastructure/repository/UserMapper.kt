package com.celia.gateway.infrastructure.repository

import com.celia.gateway.domain.model.User

fun User.toDocument(): UserDocument {
    return UserDocument(
        id = this.id,
        username = this.username,
        passwordHash = this.passwordHash,
        refreshToken = this.refreshToken
    )
}

fun UserDocument.toDomain(): User {
    return User(
        id = this.id,
        username = this.username,
        passwordHash = this.passwordHash,
        refreshToken = this.refreshToken
    )
}
