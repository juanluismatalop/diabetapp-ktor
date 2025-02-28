package com.domain.mapper

import com.data.persistence.models.UserDto
import domain.models.UpdateUser
import domain.models.User

fun User.toUpdateUser(): UpdateUser {
    return UpdateUser(
        email = email,
        contrasenna = contrasenna,
        ratioMannana = ratioMannana,
        ratioMedioDia = ratioMedioDia,
        ratioTarde = ratioTarde,
        ratioNoche = ratioNoche,
        factorSensibilidad = factorSensibilidad
    )
}

fun UpdateUser.toUser(): User {
    return User(
        email = email!!,
        contrasenna = contrasenna!!,
        ratioMannana = ratioMannana!!,
        ratioMedioDia = ratioMedioDia!!,
        ratioTarde = ratioTarde!!,
        ratioNoche = ratioNoche!!,
        factorSensibilidad = factorSensibilidad!!
    )
}

fun UserDto.toUser(): User {
    return User(
        email = this.email ?: "sin_email@example.com",
        contrasenna = this.contrasenna ?: "defaultPassword",
        ratioMannana = this.ratioMannana ?: 1.0,
        ratioMedioDia = this.ratioMedioDia ?: 1.0,
        ratioTarde = this.ratioTarde ?: 1.0,
        ratioNoche = this.ratioNoche ?: 1.0,
        factorSensibilidad = this.factorSensibilidad ?: 1.0
    )
}
