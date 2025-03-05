CREATE TABLE User (
    email VARCHAR(255) PRIMARY KEY NOT NULL,
    contrasenna VARCHAR(255) NOT NULL,
    ratioMannana DOUBLE NOT NULL,
    ratioMedioDia DOUBLE NOT NULL,
    ratioTarde DOUBLE NOT NULL,
    ratioNoche DOUBLE NOT NULL,
    factorSensibilidad DOUBLE NOT NULL
);ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='tabla de usuarios';

INSERT INTO User (email, contrasenna, ratioMannana, ratioMedioDia, ratioTarde, ratioNoche, factorSensibilidad) VALUES
('juan.perez@example.com', 'password123', 0.8, 1.2, 1.0, 0.9, 1.5),
('maria.gomez@example.com', 'securePass456', 1.0, 0.9, 1.3, 1.1, 1.7),
('carlos.rodriguez@example.com', 'carlosPass!', 0.7, 1.1, 1.2, 1.0, 1.6),
('laura.martinez@example.com', 'lauraSecret99', 0.9, 1.0, 1.1, 0.8, 1.4),
('diego.fernandez@example.com', 'diegoStrong@1', 1.2, 1.3, 0.9, 1.0, 1.8),
('ana.lopez@example.com', 'anaPassWord!', 1.1, 0.8, 1.4, 1.2, 1.9),
('pedro.garcia@example.com', 'pedroClave123', 0.8, 1.2, 1.3, 1.0, 1.5),
('sofia.torres@example.com', 'sofiaSuperKey', 1.0, 1.1, 0.9, 1.3, 1.6),
('alberto.mendez@example.com', 'albertoPass55', 1.3, 0.7, 1.2, 1.1, 1.7),
('valentina.ruiz@example.com', 'valenSecure2024', 0.9, 1.4, 1.0, 1.2, 1.8);
