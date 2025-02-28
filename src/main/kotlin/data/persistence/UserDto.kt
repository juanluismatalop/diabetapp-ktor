package com.data.persistence.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val email: String,
    val contrasenna: String,
    val ratioMannana: Double?,
    val ratioMedioDia: Double?,
    val ratioTarde: Double?,
    val ratioNoche: Double?,
    val factorSensibilidad: Double?
)
