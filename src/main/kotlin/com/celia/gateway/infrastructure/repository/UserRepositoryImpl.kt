package com.celia.gateway.infrastructure.repository

import com.celia.gateway.domain.model.User
import com.celia.gateway.domain.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val mongoUserRepository: MongoUserRepository
) : UserRepository {

    override fun findByUsername(username: String): User? {
        return mongoUserRepository.findByUsername(username)?.toDomain()
    }

    override fun findByRefreshToken(refreshToken: String): User? {
        return mongoUserRepository.findByRefreshToken(refreshToken)?.toDomain()
    }

    override fun save(user: User): User {
        return mongoUserRepository.save(user.toDocument()).toDomain()
    }


    override fun findAll(): List<User> {
        return mongoUserRepository.findAll().map { it.toDomain() }
    }

    override fun deleteAll() {
        mongoUserRepository.deleteAll()
    }
}
