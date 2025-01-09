package com.forohub.api.service;

import com.forohub.api.domain.curso.CategoriaCurso;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.curso.CursoRepository;
import com.forohub.api.domain.topico.DatosRegistroTopico;
import com.forohub.api.domain.topico.StatusTopico;
import com.forohub.api.domain.topico.Topico;
import com.forohub.api.domain.topico.TopicoRepository;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.domain.usuario.UsuarioRepository;
import com.forohub.api.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        // Obtener el curso y el usuario a partir de sus IDs
        Curso curso = cursoService.findById(datosRegistroTopico.curso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Usuario usuario = usuarioService.findById(datosRegistroTopico.usuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear el nuevo tópico usando el constructor que acepta un DatosRegistroTopico
        Topico topico = new Topico(datosRegistroTopico, curso, usuario);

        // Guardar el tópico en la base de datos
        return topicoRepository.save(topico);
    }

    public Topico actualizarEstadoTopico(Long id, DatosRegistroTopico datosRegistroTopico) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));

        if (topico.getStatus() == StatusTopico.CERRADO && datosRegistroTopico.status() == StatusTopico.ABIERTO) {
            throw new IllegalArgumentException("No puedes cambiar el estado de CERRADO a ABIERTO");
        }

        topico.cambiarEstado(datosRegistroTopico.status());
        return topicoRepository.save(topico);
    }

    public Topico obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findByIdWithRespuestas(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        topico.getRespuestas().size();

        return topico;
    }

    public Page<Topico> obtenerTodosLosTopicos(Pageable pageable) {
        Page<Topico> topicos = topicoRepository.findAll(pageable);
        return topicos;
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        topico.desactivarTopico();
        topicoRepository.save(topico);
    }
}
