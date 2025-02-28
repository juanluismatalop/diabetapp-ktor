package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email : String,
    val contrasenna : String,
    val ratioMannana : Double,
    val ratioMedioDia : Double,
    val ratioTarde : Double,
    val ratioNoche : Double,
    val factorSensibilidad : Double
)

/*
Definicion de los valores de la tabla de User que sera el usuario de la aplicacion
- primary key = email
- not null = email, contrasenna
 */