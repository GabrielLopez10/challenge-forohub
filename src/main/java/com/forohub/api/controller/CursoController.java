package com.forohub.api.controller;

import com.forohub.api.domain.curso.CategoriaCurso;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.curso.CursoRepository;
import com.forohub.api.domain.curso.DatosRegistroCurso;
import com.forohub.api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso crearCurso(@Valid @RequestBody DatosRegistroCurso datosRegistroCurso) {
        return cursoService.crearCurso(datosRegistroCurso);
    }

    @GetMapping
    public ResponseEntity<Page<Curso>> obtenerTodosLosCursos(Pageable pageable) {
        Page<Curso> cursos = cursoService.obtenerTodosLosCursos(pageable);
        if(!cursos.hasContent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cursos);
    }
}
