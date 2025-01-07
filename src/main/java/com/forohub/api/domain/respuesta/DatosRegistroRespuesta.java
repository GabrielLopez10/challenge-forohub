package com.forohub.api.domain.respuesta;

import com.forohub.api.domain.topico.Topico;
import com.forohub.api.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(

        @NotNull
        String mensaje,

        @NotNull
        boolean solucion,

        @NotNull
        Long topico,

        @NotNull
        Long autor
) {
}
