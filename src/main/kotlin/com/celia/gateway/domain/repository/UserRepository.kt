//package com.celia.gateway.domain.repository
//
//import com.celia.gateway.domain.model.User
//import org.springframework.data.mongodb.repository.MongoRepository
//
//interface UserRepository : MongoRepository<User, String> {
//    fun findByUsername(username: String): User?
//    fun findByRefreshToken(refreshToken: String): User?
//}

package com.celia.gateway.domain.repository

import com.celia.gateway.domain.model.User

interface UserRepository {
    fun findByUsername(username: String): User?
    fun findByRefreshToken(refreshToken: String): User?
    fun save(user: User): User
    fun findAll(): List<User>
    fun deleteAll()
}
