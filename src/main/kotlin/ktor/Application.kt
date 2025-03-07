package com.ktor

import com.data.inmemory.repository.MemoryUserRepository
import com.data.persistence.repository.PersistenceUserRepository
import domain.repository.UserInterface
import domain.usecase.*
import io.ktor.server.application.*
import io.ktor.server.config.yaml.*
import ktor.configureDatabases
import ktor.configureSecurity

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureDatabases()
    configureSerialization()
    configureSecurity()

    val userRepository: UserInterface = PersistenceUserRepository()
    val getAllUserUseCase = GetAllUserUseCase(userRepository)
    val getUserByEmailUseCase = GetUserByEmailUseCase(userRepository)
    val insertUserUseCase = InsertUserUseCase(userRepository)
    val deleteUserUseCase = DeleteUserUseCase(userRepository)
    val updateUserUseCase = UpdateUserUseCase(userRepository)

    configureRouting(getAllUserUseCase, getUserByEmailUseCase, insertUserUseCase, deleteUserUseCase, updateUserUseCase)
}
