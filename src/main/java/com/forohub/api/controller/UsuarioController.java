package com.forohub.api.controller;

import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para crear un usuario
    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody DatosRegistroUsuario datos) {
        Usuario usuario = usuarioService.registrarUsuario(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/{id}/perfiles")
    public ResponseEntity<Usuario> actualizarPerfiles(@PathVariable Long id, @RequestBody List<Long> perfilesIds) {
        Usuario usuario = usuarioService.actualizarPerfiles(id, perfilesIds);
        return ResponseEntity.ok(usuario);
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
