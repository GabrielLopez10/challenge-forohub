package com.forohub.api.domain.topico;

import com.forohub.api.domain.curso.CategoriaCurso;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotNull(message = "El título es obligatorio")
        String titulo,

        @NotNull(message = "El mensaje es obligatorio")
        String mensaje,

        @NotNull(message = "La fecha es obligatoria")
        LocalDateTime fecha,

        @NotNull(message = "El status es obligatorio")
        StatusTopico status,

        @NotNull
        Long curso,

        @NotNull
        Long usuario
) {
}
