package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class UpdateUserUseCase(private val repository: UserInterface) {
    operator fun invoke(user: User, email: String): Boolean = repository.updateUser(user, email)
}