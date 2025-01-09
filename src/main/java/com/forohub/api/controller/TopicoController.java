package com.forohub.api.controller;

import com.forohub.api.domain.curso.CategoriaCurso;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.topico.DatosRegistroTopico;
import com.forohub.api.domain.topico.Topico;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<Topico>> obtenerTodosLosTopicos(Pageable pageable) {
        Page<Topico> topicos = topicoService.obtenerTodosLosTopicos(pageable);
        if(!topicos.hasContent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        try {
            Topico topico = topicoService.obtenerTopicoPorId(id);
            return ResponseEntity.ok(topico);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<DatosRegistroTopico> crearTopico(@Valid @RequestBody DatosRegistroTopico datosRegistroTopico) {
        topicoService.crearTopico(datosRegistroTopico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}/estado")
    public ResponseEntity<Topico> actualizarEstado(@PathVariable Long id,
                                                   @RequestBody DatosRegistroTopico datos) {
        Topico topicoActualizado = topicoService.actualizarEstadoTopico(id, datos);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
