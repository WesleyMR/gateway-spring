package com.celia.gateway.domain.repository

import com.celia.gateway.domain.model.User

interface UserRepository {
    fun findByUsername(username: String): User?
}