package domain.repository

import domain.models.User

interface UserInterface {
    suspend fun getAllUsers () : List<User>

    suspend fun getUserByEmail (email : String) : List<User>

    suspend fun postUser(user: User) : Boolean

    suspend fun updateUser(user: User, email: String) : Boolean

    suspend fun deleteUser(email: String) : Boolean
}