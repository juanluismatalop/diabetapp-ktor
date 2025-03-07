package domain.usecase

import domain.models.User
import domain.repository.UserInterface

class GetAllUserUseCase(private val repository: UserInterface) {
    suspend operator fun invoke(): List<User> = repository.getAllUsers()
}