package com.solicitud.dinet.domain.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoSolicitud {
    @Id
    private String tipoSolicitudId;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

    public static TipoSolicitud create(String descripcion, String usuario) {
        return TipoSolicitud.builder()
                .descripcion(descripcion)
                .estado("ACTIVO")
                .fechaCreacion(LocalDateTime.now())
                .usuarioCreacion(usuario)
                .build();
    }
}
