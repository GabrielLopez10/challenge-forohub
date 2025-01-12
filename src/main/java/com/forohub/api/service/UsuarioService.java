package com.forohub.api.service;

import com.forohub.api.domain.perfil.Perfil;
import com.forohub.api.domain.perfil.PerfilRepository;
import com.forohub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

  /*  // Método para crear un nuevo usuario
    public Usuario crearUsuario(DatosRegistroUsuario datosRegistroUsuario) {
        if (usuarioRepository.existsByCorreoElectronico(datosRegistroUsuario.correoElectronico())) {
            throw new IllegalArgumentException("El usuario con el correo " + datosRegistroUsuario.correoElectronico()
                    + " ya existe");
        }
        Usuario usuario = new Usuario(datosRegistroUsuario);
        return usuarioRepository.save(usuario);
    }*/

    public Usuario registrarUsuario(DatosRegistroUsuario datos) {
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());

        Set<Perfil> perfiles = new HashSet<>(perfilRepository.findAllById(datos.perfilesIds()));

        Usuario usuario = new Usuario(datos);

        usuario.getPerfiles().addAll(perfiles);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarPerfiles(Long usuarioId, List<Long> perfilesIds) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Set<Perfil> perfiles = new HashSet<>(perfilRepository.findAllById(perfilesIds));
        usuario.getPerfiles().clear();
        usuario.getPerfiles().addAll(perfiles);

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
