package com.ktor.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("/users") {
        get {
            call.respond(
                listOf(
                    mapOf("id" to 1, "name" to "Juan", "email" to "juan@example.com"),
                    mapOf("id" to 2, "name" to "María", "email" to "maria@example.com")
                )
            )
        }

        get("/{id}") {
            val userId = call.parameters["id"]
            call.respond(mapOf("id" to userId, "name" to "Usuario $userId", "email" to "user$userId@example.com"))
        }
    }
}
