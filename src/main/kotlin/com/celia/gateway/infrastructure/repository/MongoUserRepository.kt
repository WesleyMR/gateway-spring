package com.celia.gateway.infrastructure.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoUserRepository : MongoRepository<UserDocument, String> {
    fun findByUsername(username: String): UserDocument?
    fun findByRefreshToken(refreshToken: String): UserDocument?
}
