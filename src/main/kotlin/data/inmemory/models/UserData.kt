package data.models

import domain.models.User

object UserData {
    val listUser = mutableListOf<User>(
        User(1, "juan.perez@example.com", "password123", 0.8, 1.2, 1.0, 0.9, 1.5),
        User(2, "maria.gomez@example.com", "securePass456", 1.0, 0.9, 1.3, 1.1, 1.7),
        User(3, "carlos.rodriguez@example.com", "carlosPass!", 0.7, 1.1, 1.2, 1.0, 1.6),
        User(4, "laura.martinez@example.com", "lauraSecret99", 0.9, 1.0, 1.1, 0.8, 1.4),
        User(5, "diego.fernandez@example.com", "diegoStrong@1", 1.2, 1.3, 0.9, 1.0, 1.8),
        User(6, "ana.lopez@example.com", "anaPassWord!", 1.1, 0.8, 1.4, 1.2, 1.9),
        User(7, "pedro.garcia@example.com", "pedroClave123", 0.8, 1.2, 1.3, 1.0, 1.5),
        User(8, "sofia.torres@example.com", "sofiaSuperKey", 1.0, 1.1, 0.9, 1.3, 1.6),
        User(9, "alberto.mendez@example.com", "albertoPass55", 1.3, 0.7, 1.2, 1.1, 1.7),
        User(10, "valentina.ruiz@example.com", "valenSecure2024", 0.9, 1.4, 1.0, 1.2, 1.8)
    )
}
