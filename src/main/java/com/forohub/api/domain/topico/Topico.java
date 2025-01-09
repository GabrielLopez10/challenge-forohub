package com.forohub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.forohub.api.domain.curso.Curso;
import com.forohub.api.domain.respuesta.Respuesta;
import com.forohub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fecha;

    private boolean activo;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @OneToMany(mappedBy = "topico", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Respuesta> respuestas;

    public Topico() {}

    public StatusTopico getStatus() {
        return this.status;
    }

    public List<Respuesta> getRespuestas() {
        return this.respuestas;
    }


    public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario autor) {
        this.activo = true;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = datosRegistroTopico.status();
        this.curso = curso;
        this.autor = autor;
    }

    public void cambiarEstado(StatusTopico nuevoEstado) {
        this.status = nuevoEstado;
    }

    public void desactivarTopico() {
        this.activo = false;
    }
}
