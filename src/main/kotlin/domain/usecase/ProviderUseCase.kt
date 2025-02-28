package domain.usecase

import domain.repository.UserInterface

class ProviderUseCase(
    repository: UserInterface
) {
    val getAllUsers = GetAllUserUseCase(repository)
    val getUserByEmail = GetUserByEmailUseCase(repository)
    val insertUser = InsertUserUseCase(repository)
    val updateUser = UpdateUserUseCase(repository)
    val deleteUser = DeleteUserUseCase(repository)
}