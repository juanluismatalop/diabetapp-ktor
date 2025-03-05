package com.ktor.routes

import domain.models.User
import domain.usecase.GetAllUserUseCase
import io.ktor.server.response.*
import io.ktor.server.routing.*

val user = mutableMapOf<String, User>()

fun Route.userRouting(
    getAllUserUseCase: GetAllUserUseCase
) {
    route("/users") {
        get {
            val reviews = getAllUserUseCase()
            call.respond(reviews)
        }

        get("/{email}") {
            val email = call.parameters["email"]
            val user = users[email]
            if (user != null) {
                call.respond(user)
            } else {
                call.respond(mapOf("error" to "Usuario no encontrado"))
            }
        }
    }
}