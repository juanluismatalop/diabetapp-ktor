package domain.usecase

import domain.repository.UserInterface

class DeleteUserUseCase(private val repository: UserInterface) {
    suspend operator fun invoke(email: String): Boolean = repository.deleteUser(email)
}