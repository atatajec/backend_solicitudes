package com.solicitud.dinet.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {
    @Id
    private UUID contactoId;
    private String identificador;
    private String nombre;
    private String numero;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

    public static Contacto create(String identificador, String nombre, String numero, String usuario) {
        return Contacto.builder()
                .identificador(identificador)
                .nombre(nombre)
                .numero(numero)
                .estado("ACTIVO")
                .fechaCreacion(LocalDateTime.now())
                .usuarioCreacion(usuario)
                .build();
    }
}
