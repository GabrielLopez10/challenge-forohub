CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE perfiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT NULL
);

CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

-- Insertar perfiles iniciales (opcional)
INSERT INTO perfiles (nombre, descripcion) VALUES
('ADMIN', 'Administrador con permisos completos'),
('USER', 'Usuario con acceso estándar');