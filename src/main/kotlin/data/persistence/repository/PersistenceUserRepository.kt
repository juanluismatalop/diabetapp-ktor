package com.data.persistence.repository

import com.data.persistence.models.UserTable
import com.domain.security.JwtConfig
import domain.models.User
import domain.repository.UserInterface
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class PersistenceUserRepository : UserInterface {

    // Obtener todos los usuarios
    override suspend fun getAllUsers(): List<User> = newSuspendedTransaction {
        UserTable.selectAll().map { rowToUser(it) }
    }

    // Obtener un usuario por email
    override suspend fun getUserByEmail(email: String): List<User> = newSuspendedTransaction {
        UserTable.select { UserTable.email eq email }.map { rowToUser(it) }
    }

    // Crear un nuevo usuario
    override suspend fun postUser(user: User): Boolean = newSuspendedTransaction {
        val exists = UserTable.select { UserTable.email eq user.email }.count() > 0
        if (exists) return@newSuspendedTransaction false

        UserTable.insert {
            it[email] = user.email
            it[contrasenna] = user.contrasenna // Almacena la contraseña en texto plano
            it[ratioMannana] = user.ratioMannana
            it[ratioMedioDia] = user.ratioMedioDia
            it[ratioTarde] = user.ratioTarde
            it[ratioNoche] = user.ratioNoche
            it[factorSensibilidad] = user.factorSensibilidad
        }
        true
    }

    // Actualizar un usuario existente
    override suspend fun updateUser(user: User, email: String): Boolean = newSuspendedTransaction {
        val updatedRows = UserTable.update({ UserTable.email eq email }) {
            it[contrasenna] = user.contrasenna
            it[ratioMannana] = user.ratioMannana
            it[ratioMedioDia] = user.ratioMedioDia
            it[ratioTarde] = user.ratioTarde
            it[ratioNoche] = user.ratioNoche
            it[factorSensibilidad] = user.factorSensibilidad
        }
        updatedRows > 0
    }

    // Eliminar un usuario por email
    override suspend fun deleteUser(email: String): Boolean = newSuspendedTransaction {
        val deletedRows = UserTable.deleteWhere { UserTable.email eq email }
        deletedRows > 0
    }

    // Método para login con JWT
    suspend fun login(email: String, password: String): String? = newSuspendedTransaction {
        val user = UserTable.select { UserTable.email eq email and (UserTable.contrasenna eq password) }
            .map { rowToUser(it) }
            .firstOrNull()

        user?.let { JwtConfig.generateToken(it.email) }
    }

    // Convertir una fila de la base de datos a un objeto User
    private fun rowToUser(row: ResultRow): User = User(
        email = row[UserTable.email],
        contrasenna = row[UserTable.contrasenna],
        ratioMannana = row[UserTable.ratioMannana],
        ratioMedioDia = row[UserTable.ratioMedioDia],
        ratioTarde = row[UserTable.ratioTarde],
        ratioNoche = row[UserTable.ratioNoche],
        factorSensibilidad = row[UserTable.factorSensibilidad]
    )
}