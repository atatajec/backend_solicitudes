package com.solicitud.dinet.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
    @Id
    private UUID solicitudId;
    private String codigoSolicitud;
    private UUID marcaId;
    private UUID tipoSolicitudId;
    private LocalDate fechaEnvio;
    private String estado;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;

    public static Solicitud create(String codigoSolicitud, UUID marcaId, 
                                 UUID tipoSolicitudId, LocalDate fechaEnvio, String usuario) {
        return Solicitud.builder()
                .codigoSolicitud(codigoSolicitud)
                .marcaId(marcaId)
                .tipoSolicitudId(tipoSolicitudId)
                .fechaEnvio(fechaEnvio)
                .estado("ACTIVO")
                .fechaCreacion(LocalDateTime.now())
                .usuarioCreacion(usuario)
                .build();
    }
    
    public void update(UUID marcaId, UUID tipoSolicitudId, 
                      LocalDate fechaEnvio, String usuario) {
        this.marcaId = marcaId;
        this.tipoSolicitudId = tipoSolicitudId;
        this.fechaEnvio = fechaEnvio;
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }
    
    public void changeState(String newState, String usuario) {
        this.estado = newState;
        this.fechaModificacion = LocalDateTime.now();
        this.usuarioModificacion = usuario;
    }
}
