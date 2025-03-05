package com.ktor

import com.data.inmemory.repository.MemoryUserRepository
import domain.repository.UserInterface
import domain.usecase.GetAllUserUseCase
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()


    val userRepository: UserInterface = MemoryUserRepository()
    val getAllUserUseCase= GetAllUserUseCase(userRepository)
    configureRouting(getAllUserUseCase)
}
