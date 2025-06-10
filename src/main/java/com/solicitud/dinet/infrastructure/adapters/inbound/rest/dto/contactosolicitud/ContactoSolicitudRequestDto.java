package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud;

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
@Schema(description = "Request DTO for creating or updating a Contacto Solicitud")
public class ContactoSolicitudRequestDto {

    @NotNull(message = "SolicitudId is required")
    @Positive(message = "SolicitudId must be positive")
    @Schema(description = "Type of SolicitudId", example = "UUID")
    private String solicitudId;

    @NotNull(message = "ContactoId is required")
    @Positive(message = "ContactoId must be positive")
    @Schema(description = "Type of ContactoId", example = "UUID")
    private String contactoId;

    @Schema(description = "User who performs the operation", example = "SYSTEM")
    private String usuario;
}
