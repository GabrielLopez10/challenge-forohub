package com.forohub.api.service;

import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.domain.usuario.UsuarioRepository;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    // Método para crear un nuevo usuario
    public Usuario crearUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        if (usuarioRepository.existsByCorreoElectronico(datosRegistroUsuario.correoElectronico())) {
            throw new IllegalArgumentException("El usuario con el correo " + datosRegistroUsuario.correoElectronico()
                    + " ya existe");
        }
        Usuario usuario = new Usuario(datosRegistroUsuario);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Page<Usuario> obtenerTodosLosUsuarios(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios;
    }
}
