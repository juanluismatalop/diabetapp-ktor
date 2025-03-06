package com.data.inmemory.repository

import data.models.UserData
import domain.models.User
import domain.repository.UserInterface

class MemoryUserRepository : UserInterface {

    override suspend fun getAllUsers(): List<User> {
        return UserData.listUser
    }

    override suspend fun getUserByEmail(email: String): List<User> {
        return UserData.listUser.filter { it.email == email }
    }

    override suspend fun postUser(user: User): Boolean {
        val existingUser = getUserByEmail(user.email)
        return if (existingUser.isNotEmpty()) {
            false
        } else {
            UserData.listUser.add(user)
            true
        }
    }

    override suspend fun updateUser(user: User, email: String): Boolean {
        val index = UserData.listUser.indexOfFirst { it.email == email }
        return if (index != -1) {
            val existingUser = UserData.listUser[index]
            UserData.listUser[index] = existingUser.copy(
                contrasenna = user.contrasenna ?: existingUser.contrasenna,
                ratioMannana = user.ratioMannana ?: existingUser.ratioMannana,
                ratioMedioDia = user.ratioMedioDia ?: existingUser.ratioMedioDia,
                ratioTarde = user.ratioTarde ?: existingUser.ratioTarde,
                ratioNoche = user.ratioNoche ?: existingUser.ratioNoche,
                factorSensibilidad = user.factorSensibilidad ?: existingUser.factorSensibilidad
            )
            true
        } else {
            false
        }
    }

    override suspend fun deleteUser(email: String): Boolean {
        val index = UserData.listUser.indexOfFirst { it.email == email }
        return if (index != -1) {
            UserData.listUser.removeAt(index)
            true
        } else {
            false
        }
    }
}
