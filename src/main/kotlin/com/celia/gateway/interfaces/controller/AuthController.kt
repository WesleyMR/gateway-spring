package com.celia.gateway.interfaces.controller

import com.celia.gateway.application.LoginService
import com.celia.gateway.interfaces.dto.LoginRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class AuthController(
    private val loginService: LoginService
) {
    @PostMapping
    fun login(@RequestBody request: LoginRequest): String {
        return loginService.login(request.username, request.password)
    }
}