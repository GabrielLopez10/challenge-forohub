package com.forohub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"curso", "autor", "respuestas"})
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario autor;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;

    public Topico() {}

    public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario autor) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = datosRegistroTopico.fecha();
        this.status = datosRegistroTopico.status();
        this.curso = curso;
        this.autor = autor;
    }
}
