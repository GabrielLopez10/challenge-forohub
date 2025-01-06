package com.forohub.api.controller;

import com.forohub.api.domain.curso.CategoriaCurso;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.topico.DatosRegistroTopico;
import com.forohub.api.domain.topico.Topico;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping()
    public ResponseEntity<List<Topico>> obtenerTodosLosTopicos() {
        List<Topico> topicos = topicoService.obtenerTodosLosTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obtenerTopicoPorId(id);
        return topico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico) {
        topicoService.crearTopico(datosRegistroTopico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
