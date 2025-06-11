package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO for solicitud")
public class SolicitudDetalleResponseDto {

    @Schema(description = "Codigo", example = "SOL-ABC12345")
    private String codigosolicitud;

    @Schema(description = "Marca", example = "DINER")
    private String marca;

    @Schema(description = "Fecha envío", example = "2025-06-11")
    private LocalDate fechaenvio;

    @Schema(description = "Tipo", example = "TIPO 1")
    private String tipo;

    @Schema(description = "Nombre", example = "ALEXIS TATAJE")
    private String nombrecontacto;

    @Schema(description = "Número", example = "939733228")
    private String numerocontacto;

}
