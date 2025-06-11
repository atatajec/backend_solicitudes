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
public class ContactoSolicitud {
    @Id
    private UUID contactoSolicitudId;
    private Integer tipo;
    private UUID solicitudId;
    private UUID contactoId;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

    public static ContactoSolicitud create(Integer tipo, UUID solicitudId, UUID contactoId, String usuario) {
        return ContactoSolicitud.builder()
                .tipo(tipo)
                .solicitudId(solicitudId)
                .contactoId(contactoId)
                .estado("ACTIVO")
                .fechaCreacion(LocalDateTime.now())
                .usuarioCreacion(usuario)
                .build();
    }
}
