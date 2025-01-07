package com.forohub.api.service;

import com.forohub.api.domain.respuesta.DatosRegistroRespuesta;
import com.forohub.api.domain.respuesta.Respuesta;
import com.forohub.api.domain.respuesta.RespuestaRepository;
import com.forohub.api.domain.topico.StatusTopico;
import com.forohub.api.domain.topico.Topico;
import com.forohub.api.domain.topico.TopicoRepository;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Respuesta registrarRespuesta(DatosRegistroRespuesta datos) {

        Topico topico = topicoRepository.findById(datos.topico())
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        if(topico.getStatus() != StatusTopico.ABIERTO) {
            throw new IllegalArgumentException("No se pueden agregar respuestas a un tópico cerrado");
        }

        Usuario usuario = usuarioRepository.findById(datos.autor())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        if (datos.solucion() && topico.getRespuestas().stream().anyMatch(Respuesta::isSolucion)) {
            throw new IllegalArgumentException("Ya existe una respuesta marcada como solución para este tópico");
        }

        Respuesta respuesta = new Respuesta(datos, usuario, topico);
        return respuestaRepository.save(respuesta);
    }
}
