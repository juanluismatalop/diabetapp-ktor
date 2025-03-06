package com.ktor

import com.ktor.routes.authRouting
import com.ktor.routes.userRouting
import domain.models.UpdateUser
import domain.usecase.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    getAllUserUseCase: GetAllUserUseCase,
    getUserByEmailUseCase: GetUserByEmailUseCase,
    insertUserUseCase: InsertUserUseCase,
    deleteUserUseCase: DeleteUserUseCase,
    updateUserUseCase: UpdateUserUseCase
)
     {
    routing {
        // Ruta principal
        get("/") {
            call.respondText("Hello World!")
        }

        // Rutas de autenticación
        authRouting()

        // Rutas de usuario
        userRouting(getAllUserUseCase, getUserByEmailUseCase, insertUserUseCase, deleteUserUseCase, updateUserUseCase)

        // Recursos estáticos (Ej: HTML, CSS, JS)
        staticResources("/static", "static")
    }
}
