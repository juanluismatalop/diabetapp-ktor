package com.data.persistence.repository

import com.data.persistence.models.UserTable
import com.domain.security.JwtConfig
import domain.models.User
import domain.repository.UserInterface
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.mindrot.jbcrypt.BCrypt

class PersistenceUserRepository : UserInterface {

    override suspend fun getAllUsers(): List<User> = newSuspendedTransaction {
        UserTable.selectAll().map { it.toUser() }
    }

    override suspend fun getUserByEmail(email: String): List<User> = newSuspendedTransaction {
        UserTable.select { UserTable.email eq email }
            .map { it.toUser() }
    }

    override suspend fun postUser(user: User): Boolean = newSuspendedTransaction {
        val exists = UserTable.select { UserTable.email eq user.email }.count() > 0
        if (exists) return@newSuspendedTransaction false

        val hashedPassword = BCrypt.hashpw(user.contrasenna, BCrypt.gensalt())

        UserTable.insert {
            it[email] = user.email
            it[contrasenna] = hashedPassword
            it[ratioMannana] = user.ratioMannana ?: 0.0
            it[ratioMedioDia] = user.ratioMedioDia ?: 0.0
            it[ratioTarde] = user.ratioTarde ?: 0.0
            it[ratioNoche] = user.ratioNoche ?: 0.0
            it[factorSensibilidad] = user.factorSensibilidad ?: 0.0
        }
        true
    }

    override suspend fun updateUser(user: User, email: String): Boolean = newSuspendedTransaction {
        val hashedPassword = BCrypt.hashpw(user.contrasenna, BCrypt.gensalt())

        val updatedRows = UserTable.update({ UserTable.email eq email }) {
            it[contrasenna] = hashedPassword
            it[ratioMannana] = user.ratioMannana ?: 0.0
            it[ratioMedioDia] = user.ratioMedioDia ?: 0.0
            it[ratioTarde] = user.ratioTarde ?: 0.0
            it[ratioNoche] = user.ratioNoche ?: 0.0
            it[factorSensibilidad] = user.factorSensibilidad ?: 0.0
        }
        updatedRows > 0
    }

    override suspend fun deleteUser(email: String): Boolean = newSuspendedTransaction {
        val deletedRows = UserTable.deleteWhere { UserTable.email eq email }
        deletedRows > 0
    }

    suspend fun login(email: String, password: String): String? = newSuspendedTransaction {
        val user = UserTable.select { UserTable.email eq email }
            .map { it.toUser() }
            .singleOrNull()

        user?.let {
            if (BCrypt.checkpw(password, it.contrasenna)) {
                JwtConfig.generateToken(it.email)
            } else null
        }
    }
}

// Función de extensión para convertir ResultRow en User
fun ResultRow.toUser(): User = User(
    id = this[UserTable.id].value,
    email = this[UserTable.email],
    contrasenna = this[UserTable.contrasenna], // Ahora está hasheada
    ratioMannana = this[UserTable.ratioMannana],
    ratioMedioDia = this[UserTable.ratioMedioDia],
    ratioTarde = this[UserTable.ratioTarde],
    ratioNoche = this[UserTable.ratioNoche],
    factorSensibilidad = this[UserTable.factorSensibilidad]
)
