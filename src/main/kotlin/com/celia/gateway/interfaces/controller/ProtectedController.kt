package com.celia.gateway.interfaces.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/protected")
class ProtectedController { // 👈 precisa dessa classe aqui!

    @GetMapping
    fun protectedEndpoint(): String {
        return "Você acessou um endpoint protegido com sucesso! 🔒"
    }
}
