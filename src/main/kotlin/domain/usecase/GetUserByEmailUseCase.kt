package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class GetUserByEmailUseCase(private val repository: UserInterface) {
    operator fun invoke(email: String): List<User> = repository.getUserByEmail(email)
}