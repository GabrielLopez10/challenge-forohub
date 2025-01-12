package com.forohub.api.controller;

import com.forohub.api.domain.respuesta.DatosRegistroRespuesta;
import com.forohub.api.domain.respuesta.Respuesta;
import com.forohub.api.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<Respuesta> registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos) {
        Respuesta respuesta = respuestaService.registrarRespuesta(datos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
