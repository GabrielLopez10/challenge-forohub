package com.forohub.api.SecurityAutentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forohub.api.domain.usuario.DatosLoginUsuario;
import com.forohub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.api.domain.usuario.Usuario;
import com.forohub.api.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ApiAutenticationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setNombre("test");
        usuario.setCorreoElectronico("test@example.com");
        usuario.setContrasena(passwordEncoder.encode("password123"));
        usuarioRepository.save(usuario);
    }

    @Test
    void testAccesoConAutenticacion() throws Exception {
        var token = obtenerTokenValido();

        mockMvc.perform(get("/topicos")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    private String obtenerTokenValido() throws Exception {
        var datosRegistroUsuario = new DatosLoginUsuario("test@example.com", "password123");
        var contenido = new ObjectMapper().writeValueAsString(datosRegistroUsuario);

        var resultado = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contenido))
                .andReturn();

        System.out.println("Response body: " + resultado.getResponse().getContentAsString());

        var responseBody = resultado.getResponse().getContentAsString();
        var jsonNode = new ObjectMapper().readTree(responseBody);

        if (jsonNode.has("jwTtoken")) {
            return jsonNode.get("jwTtoken").asText();
        } else {
            throw new IllegalStateException("El campo 'jwTtoken' no está presente en la respuesta: " + responseBody);
        }
    }
}
