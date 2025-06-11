package com.solicitud.dinet.domain.models;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitudFiltro {
    private String marca;
    private String tipo;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
