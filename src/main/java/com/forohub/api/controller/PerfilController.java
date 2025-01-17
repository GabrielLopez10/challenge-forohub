package com.forohub.api.controller;

import com.forohub.api.domain.perfil.Perfil;
import com.forohub.api.domain.perfil.PerfilRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<List<Perfil>> obtenerTodosLosPerfiles() {
        return ResponseEntity.ok(perfilRepository.findAll());
    }
}
