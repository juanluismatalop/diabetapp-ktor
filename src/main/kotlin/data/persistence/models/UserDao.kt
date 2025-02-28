package com.data.persistence.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserDao>(UserTable) // Métodos de acceso a la BBDD

    var email by UserTable.email
    var contrasenna by UserTable.contrasenna
    var ratioMannana by UserTable.ratioMannana
    var ratioMedioDia by UserTable.ratioMedioDia
    var ratioTarde by UserTable.ratioTarde
    var ratioNoche by UserTable.ratioNoche
    var factorSensibilidad by UserTable.factorSensibilidad
}
