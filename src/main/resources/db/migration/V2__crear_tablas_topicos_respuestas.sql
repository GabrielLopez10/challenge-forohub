CREATE TABLE IF NOT EXISTS cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha TIMESTAMP NOT NULL,
    activo BOOLEAN DEFAULT true,
    status VARCHAR(255) NOT NULL,
    curso_id BIGINT,
    usuario_id BIGINT,
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id),
    CONSTRAINT fk_usuario_topico FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Agregar tabla de respuestas
CREATE TABLE IF NOT EXISTS respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    solucion BOOLEAN DEFAULT false,
    topico_id BIGINT,
    usuario_id BIGINT,
    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_usuario_respuesta FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
