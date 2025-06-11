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
    private String codigosolicitud;
    private String marca;
    private LocalDate fechaenvio;
    private String tipo;
    private String nombrecontacto;
    private String numerocontacto;
}
