package com.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("User") {
    val email = varchar("email", 255).uniqueIndex() // Campo único y obligatorio
    val contrasenna = varchar("contrasenna", 255)
    val ratioMannana = double("ratio_mannana").default(0.0)
    val ratioMedioDia = double("ratio_medio_dia").default(0.0)
    val ratioTarde = double("ratio_tarde").default(0.0)
    val ratioNoche = double("ratio_noche").default(0.0)
    val factorSensibilidad = double("factor_sensibilidad").default(1.0)
}
