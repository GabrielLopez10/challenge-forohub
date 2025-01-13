package com.forohub.api.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosLoginUsuario(

        @NotNull(message = "El correo electrónico es obligatorio")
        String correoElectronico,

        @NotNull(message = "La contraseña es obligatoria")
        String contrasena
) {}
