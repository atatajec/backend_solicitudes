package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDetalleEntity {

    private String codigoSolicitud;
    private String marca;
    private LocalDate fechaEnvio;
    private String tipo;
    private String nombreContacto;
    private String numeroContacto;

}
