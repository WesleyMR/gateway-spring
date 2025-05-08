package com.celia.gateway.application

import com.celia.gateway.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepository
) {
    fun login(username: String, password: String): String {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("Usuário não encontrado")

        // Aqui futuramente você vai usar bcrypt para validar senha
        if (password != "1234") throw IllegalArgumentException("Senha inválida")

        // Aqui futuramente você vai gerar o JWT
        return "token-jwt-fake"
    }
}