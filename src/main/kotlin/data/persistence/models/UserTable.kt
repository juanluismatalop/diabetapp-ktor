package com.data.persistence.models

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable("User") {
    val email = varchar("email", 255).uniqueIndex()
    val contrasenna = varchar("contrasenna", 255)
    val ratioMannana = double("ratioMannana").default(0.0)
    val ratioMedioDia = double("ratioMedioDia").default(0.0)
    val ratioTarde = double("ratioTarde").default(0.0)
    val ratioNoche = double("ratioNoche").default(0.0)
    val factorSensibilidad = double("factorSensibilidad").default(1.0)
}
