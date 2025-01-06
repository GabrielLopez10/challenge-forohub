package com.forohub.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotNull
        String nombre,

        @NotNull
        CategoriaCurso categoria
) {
}
