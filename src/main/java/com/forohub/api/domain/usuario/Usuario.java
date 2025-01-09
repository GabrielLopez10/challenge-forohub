package com.forohub.api.domain.usuario;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forohub.api.domain.perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String correoElectronico;

    private String contrasena;

    @ManyToMany
    @JoinTable(
            name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private Set<Perfil> perfiles = new HashSet<>();

    public Usuario() {
    }

    public Set<Perfil> getPerfiles() {
        return this.perfiles;
    }

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.nombre = datosRegistroUsuario.nombre();
        this.correoElectronico = datosRegistroUsuario.correoElectronico();
        this.contrasena = datosRegistroUsuario.contrasena();
    }

    public void agregarPerfil(Perfil perfil) {
        perfiles.add(perfil);
    }

    public void eliminarPerfil(Perfil perfil) {
        perfiles.remove(perfil);
    }
}
