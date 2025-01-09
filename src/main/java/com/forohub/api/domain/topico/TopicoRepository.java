package com.forohub.api.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByActivoTrue(Pageable paginacion);

    @Query("SELECT t FROM Topico t " +
            "LEFT JOIN FETCH t.curso " +
            "LEFT JOIN FETCH t.usuario " +
            "LEFT JOIN FETCH t.respuestas  " +
            "WHERE t.id = :id")
    Optional<Topico> findByIdWithRespuestas(@Param("id") Long id);

    List<Topico> findAll();
}
