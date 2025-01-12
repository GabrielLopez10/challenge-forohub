package com.forohub.api.domain.usuario;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record DatosRegistroUsuario(
        String nombre,

        @NotNull(message = "El correo electrónico es obligatorio")
        String correoElectronico,

        @NotNull(message = "La contraseña es obligatoria")
        String contrasena,
        
        List<Long> perfilesIds

) {
}
