package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class InsertUserUseCase(private val repository: UserInterface) {
    operator fun invoke(user: User): Boolean = repository.postUser(user)
}