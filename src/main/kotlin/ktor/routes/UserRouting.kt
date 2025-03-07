package com.ktor.routes

import domain.models.User
import domain.usecase.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(
    getAllUserUseCase: GetAllUserUseCase,
    getUserByEmailUseCase: GetUserByEmailUseCase,
    insertUserUseCase: InsertUserUseCase,
    deleteUserUseCase: DeleteUserUseCase,
    updateUserUseCase: UpdateUserUseCase
) {
    route("/users") {
        // Obtener todos los usuarios
        get {
            val users = getAllUserUseCase()
            call.respond(users)
        }

        // Obtener un usuario por email
        get("/{email}") {
            val email = call.parameters["email"]
            if (email.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email requerido"))
                return@get
            }

            val user = getUserByEmailUseCase(email)
            if (user.isNotEmpty()) {
                call.respond(user)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Usuario no encontrado"))
            }
        }

        // Crear un usuario
        post {
            try {
                val user = call.receive<User>()
                println("Recibida solicitud para crear usuario: ${user.email}")
                val result = insertUserUseCase(user)
                if (result) {
                    call.respond(HttpStatusCode.Created, mapOf("message" to "Usuario creado exitosamente"))
                } else {
                    call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Error al crear usuario"))
                }
            } catch (e: Exception) {
                println("Error en la ruta POST /users: ${e.message}")
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Datos inválidos o error en la solicitud"))
            }
        }

        // Actualizar un usuario por email
        put("/{email}") {
            val email = call.parameters["email"]
            if (email.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email requerido"))
                return@put
            }

            val updatedUser = call.receive<User>()
            val result = updateUserUseCase(updatedUser, email)
            if (result) {
                call.respond(HttpStatusCode.OK, mapOf("message" to "Usuario actualizado exitosamente"))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Usuario no encontrado"))
            }
        }

        // Eliminar un usuario por email
        delete("/{email}") {
            val email = call.parameters["email"]
            if (email.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to "Email requerido"))
                return@delete
            }

            val result = deleteUserUseCase(email)
            if (result) {
                call.respond(HttpStatusCode.OK, mapOf("message" to "Usuario eliminado exitosamente"))
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to "Usuario no encontrado"))
            }
        }
    }
}
