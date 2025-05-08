package com.celia.gateway.infrastructure.repository

import com.celia.gateway.domain.model.User
import com.celia.gateway.domain.repository.UserRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface SpringUserMongoRepository : MongoRepository<UserDocument, String> {
    fun findByUsername(username: String): UserDocument?
}

@Repository
class MongoUserRepository(
    private val springRepo: SpringUserMongoRepository
) : UserRepository {

    override fun findByUsername(username: String): User? {
        return springRepo.findByUsername(username)?.toDomain()
    }
}