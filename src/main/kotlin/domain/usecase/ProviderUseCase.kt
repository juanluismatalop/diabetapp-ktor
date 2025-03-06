package domain.usecase

import com.data.persistence.repository.PersistenceUserRepository

class ProviderUseCase(
    repository: PersistenceUserRepository // Cambiar el tipo aquí
) {
    val getAllUsers = GetAllUserUseCase(repository)
    val getUserByEmail = GetUserByEmailUseCase(repository)
    val insertUser = InsertUserUseCase(repository)
    val updateUser = UpdateUserUseCase(repository)
    val deleteUser = DeleteUserUseCase(repository)
}