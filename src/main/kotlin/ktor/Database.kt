package ktor

import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

fun Application.configureDatabases() {
    val log = LoggerFactory.getLogger("DatabaseConfig")

    try {
        val driver = environment.config.property("ktor.database.driver").getString()
        val url = environment.config.property("ktor.database.url").getString()
        val user = environment.config.property("ktor.database.user").getString()
        val password = environment.config.property("ktor.database.password").getString()

        Database.connect(
            url = url,
            driver = driver,
            user = user,
            password = password
        )

        log.info("Conexión a la base de datos establecida correctamente.")
    } catch (e: ApplicationConfigurationException) {
        log.error("Error en la configuración: ${e.message}", e)
        throw e
    } catch (e: Exception) {
        log.error("Error al conectar a la base de datos: ${e.message}", e)
        throw e
    }
}