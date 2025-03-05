package com.ktor.routes

import domain.models.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Simulación de una base de datos en memoria
val users = mutableMapOf<String, User>()

fun Route.authRouting() {
    route("/auth") {
        post("/login") {
            val request = call.receive<Map<String, String>>()
            val email = request["email"]
            val contrasenna = request["contrasenna"]

            val user = users[email]
            if (user != null && user.contrasenna == contrasenna) {
                call.respond(mapOf("message" to "Login exitoso", "token" to "fake-jwt-token"))
            } else {
                call.respond(mapOf("error" to "Credenciales incorrectas"))
            }
        }

        post("/register") {
            val user = call.receive<User>()
            if (users.containsKey(user.email)) {
                call.respond(mapOf("error" to "El usuario ya está registrado"))
            } else {
                users[user.email] = user
                call.respond(mapOf("message" to "Usuario registrado con éxito", "email" to user.email))
            }
        }
    }
}