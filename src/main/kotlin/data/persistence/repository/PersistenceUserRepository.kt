package com.data.persistence.repository

import com.data.persistence.models.UserTable


import com.domain.security.JwtConfig
import domain.models.User
import domain.repository.UserInterface
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class PersistenceUserRepository : UserInterface {

    override fun getAllUsers(): List<User> = transaction {
        UserTable.selectAll().map { rowToUser(it) }
    }

    override fun getUserByEmail(email: String): List<User> = transaction {
        UserTable.select { UserTable.email eq email }
            .map { rowToUser(it) }
    }

    override fun postUser(user: User): Boolean = transaction {
        val exists = UserTable.select { UserTable.email eq user.email }.count() > 0
        if (exists) return@transaction false

        UserTable.insert {
            it[email] = user.email
            it[contrasenna] = user.contrasenna // Almacena directamente la contraseña
            it[ratioMannana] = user.ratioMannana
            it[ratioMedioDia] = user.ratioMedioDia
            it[ratioTarde] = user.ratioTarde
            it[ratioNoche] = user.ratioNoche
            it[factorSensibilidad] = user.factorSensibilidad
        }
        true
    }

    override fun updateUser(user: User, email: String): Boolean = transaction {
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

    override fun deleteUser(email: String): Boolean = transaction {
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
