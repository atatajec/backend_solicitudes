package com.solicitud.dinet.domain.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudDetalle {
    private String codigoSolicitud;
    private String marca;
    private LocalDate fechaEnvio;
    private String tipo;
    private String nombreContacto;
    private String numeroContacto;
}
