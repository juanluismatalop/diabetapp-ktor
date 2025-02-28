package domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUser(
    val email : String? = null,
    val contrasenna : String? = null,
    val ratioMannana : Double? = null,
    val ratioMedioDia : Double? = null,
    val ratioTarde : Double? = null,
    val ratioNoche : Double? = null,
    val factorSensibilidad : Double? = null
)