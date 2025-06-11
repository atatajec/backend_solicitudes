package com.solicitud.dinet.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDetalle {
    private String codigoSolicitud;
    private String marca;
    private LocalDate fechaEnvio;
    private String tipo;
    private String nombreContacto;
    private String numeroContacto;
}
