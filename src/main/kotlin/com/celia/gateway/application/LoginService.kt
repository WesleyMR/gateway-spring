package com.celia.gateway.application

import com.celia.gateway.domain.repository.UserRepository
import com.celia.gateway.infrastructure.jwt.JwtUtil
import com.celia.gateway.interfaces.dto.LoginResponse
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil
) {
    fun login(username: String, password: String): LoginResponse {
        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("Usuário não encontrado")

        if (!BCrypt.checkpw(password, user.passwordHash)) {
            throw IllegalArgumentException("Senha inválida")
        }

        val accessToken = jwtUtil.generateToken(user.username, user.id ?: "")
        val refreshToken = UUID.randomUUID().toString()

        val updatedUser = user.copy(refreshToken = refreshToken)
        userRepository.save(updatedUser)

        return LoginResponse(
            status = 200,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }


    fun register(username: String, password: String): String {
        if (userRepository.findByUsername(username) != null) {
            throw IllegalArgumentException("Usuário já existe")
        }

        val hash = BCrypt.hashpw(password, BCrypt.gensalt())
        userRepository.save(com.celia.gateway.domain.model.User(username = username, passwordHash = hash))
        return "Usuário registrado com sucesso"
    }

    fun refresh(refreshToken: String): LoginResponse {
        val user = userRepository.findByRefreshToken(refreshToken)
            ?: throw IllegalArgumentException("Refresh token inválido")

        val newAccessToken = jwtUtil.generateToken(user.username, user.id ?: "")

        return LoginResponse(
            status = 200,
            accessToken = newAccessToken,
            refreshToken = refreshToken // 🔵 Você pode manter ou gerar novo se quiser
        )
    }
}
