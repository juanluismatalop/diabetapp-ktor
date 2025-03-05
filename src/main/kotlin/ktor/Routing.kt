package com.ktor

import com.ktor.routes.authRouting
import com.ktor.routes.userRouting
import domain.usecase.GetAllUserUseCase
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    getAllUserUseCase: GetAllUserUseCase
)
     {
    routing {
        // Ruta principal
        get("/") {
            call.respondText("Hello World!")
        }

        // Rutas de autenticación
        authRouting()

        // Rutas de empleados
        userRouting(getAllUserUseCase)

        // Recursos estáticos (Ej: HTML, CSS, JS)
        staticResources("/static", "static")
    }
}
