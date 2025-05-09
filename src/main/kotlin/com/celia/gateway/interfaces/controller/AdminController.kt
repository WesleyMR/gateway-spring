package com.celia.gateway.interfaces.controller

import com.celia.gateway.domain.repository.UserRepository
import com.celia.gateway.interfaces.dto.UserResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val userRepository: UserRepository
) {

    @GetMapping("/users")
    fun listUsers(): List<UserResponse> {
        return userRepository.findAll().map { user ->
            UserResponse(
                id = user.id ?: "",
                username = user.username
            )
        }
    }

    @GetMapping("/users/clear")
    fun clearUsers(): String {
        userRepository.deleteAll()
        return "Todos os usuários foram apagados."
    }
}
