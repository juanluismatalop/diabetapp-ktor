package domain.repository

import domain.models.User

interface UserInterface {
    fun getAllUsers () : List<User>

    fun getUserByEmail (email : String) : List<User>

    fun postUser(user: User) : Boolean

    fun updateUser(user: User, email: String) : Boolean

    fun deleteUser(email: String) : Boolean
}