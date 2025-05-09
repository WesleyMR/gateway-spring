package com.celia.gateway.interfaces.controller

import com.celia.gateway.application.LoginService
import com.celia.gateway.domain.repository.UserRepository
import com.celia.gateway.interfaces.dto.*
import com.celia.gateway.interfaces.dto.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/auth")
class AuthController(
    private val loginService: LoginService,
    private val userRepository: UserRepository
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val response = loginService.login(request.username, request.password)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): String {
        return loginService.register(request.username, request.password)
    }

    @GetMapping("/me")
    fun me(principal: Principal): ResponseEntity<MeResponse> {
        val username = principal.name

        val user = userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("Usuário não encontrado")

        return ResponseEntity.ok(
            MeResponse(
                id = user.id ?: "N/A",
                username = user.username,
                refreshToken = user.refreshToken
            )
        )
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest): ResponseEntity<LoginResponse> {
        val response = loginService.refresh(request.refreshToken)
        return ResponseEntity.ok(response)
    }
}