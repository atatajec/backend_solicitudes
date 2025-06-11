package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO solicitud")
public class SolicitudDetalleRequestDto {

    @NotNull(message = "Marca is required")
    @Positive(message = "Marca must be positive")
    @Schema(description = "marca", example = "DINET")
    private String marca;

    @NotNull(message = "Tipo is required")
    @Positive(message = "Tipo must be positive")
    @Schema(description = "tipo", example = "TIPO 1")
    private String tipo;

    @NotNull(message = "Fecha desde is required")
    @Positive(message = "Fecha desde must be positive")
    @Schema(description = "Fecha desde", example = "2025-06-07")
    private LocalDate fechaDesde;

    @NotNull(message = "Tipo is required")
    @Positive(message = "Tipo must be positive")
    @Schema(description = "tipo", example = "2025-06-10")
    private LocalDate fechaHasta;
}
