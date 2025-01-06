package com.forohub.api.service;

import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.curso.CursoRepository;
import com.forohub.api.domain.curso.DatosRegistroCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;


    public Curso crearCurso(DatosRegistroCurso datosRegistroCurso) {

        if(cursoRepository.existsByNombreAndCategoria(datosRegistroCurso.nombre(), datosRegistroCurso.categoria())) {
            throw new IllegalArgumentException("El curso " + datosRegistroCurso.nombre()
                    + " en la categoria " + datosRegistroCurso.categoria() + " ya existe");
        }

        Curso curso = new Curso(datosRegistroCurso);
        return cursoRepository.save(curso);
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Page<Curso> obtenerTodosLosCursos(Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findAll(pageable);
        return cursos;
    }
}
