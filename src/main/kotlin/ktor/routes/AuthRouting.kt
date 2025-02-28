package com.ktor.routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRouting() {
    route("/auth") {
        post("/login") {
            val request = call.receive<Map<String, String>>() // Recibir credenciales
            val username = request["username"]
            val password = request["password"]

            if (username == "admin" && password == "password") {
                call.respond(mapOf("message" to "Login exitoso", "token" to "fake-jwt-token"))
            } else {
                call.respond(mapOf("error" to "Credenciales incorrectas"))
            }
        }

        post("/register") {
            val request = call.receive<Map<String, String>>() // Recibir datos de registro
            val username = request["username"]
            val email = request["email"]

            call.respond(mapOf("message" to "Usuario $username registrado con éxito", "email" to email))
        }
    }
}
