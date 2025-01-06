package com.forohub.api.domain.usuario;

import com.forohub.api.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoElectronico(String correoElectronico);
}
