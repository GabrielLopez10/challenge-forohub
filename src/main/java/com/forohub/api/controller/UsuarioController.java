package com.forohub.api.controller;

import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para crear un usuario
    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody DatosRegistroUsuario datosRegistroUsuario) {
        return usuarioService.crearUsuario(datosRegistroUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> obtenerTodosLosCursos(Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios(pageable);
        if(!usuarios.hasContent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }
}
