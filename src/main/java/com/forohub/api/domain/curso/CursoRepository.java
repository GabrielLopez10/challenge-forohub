package com.forohub.api.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNombreAndCategoria(String nombre, CategoriaCurso categoria);
}
