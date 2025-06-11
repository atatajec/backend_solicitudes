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
public class SolicitudFiltro {
    private String marca;
    private String tipo;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
