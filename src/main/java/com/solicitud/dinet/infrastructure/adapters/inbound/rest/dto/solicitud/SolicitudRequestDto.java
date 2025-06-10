package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Schema(description = "Request DTO for creating or updating a solicitud")
public class SolicitudRequestDto {
    
@NotNull(message = "Marca ID is required")
    @Positive(message = "Marca ID must be positive")
    @Schema(description = "ID of the marca", example = "1")
    private String marcaId;
    
    @NotNull(message = "Tipo solicitud is required")
    @Positive(message = "Tipo solicitud must be positive")
    @Schema(description = "Type of solicitud", example = "1")
    private String tipoSolicitudId;
    
    @NotNull(message = "Fecha envio is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date when solicitud was sent", example = "2024-01-15")
    private LocalDate fechaEnvio;

    @NotNull(message = "Contacto is required")
    @Positive(message = "Contacto must be positive")
    @Schema(description = "Type of contacto", example = "Alexis Tataje")
    private String nombre;

    @NotNull(message = "Numero Contacto is required")
    @Positive(message = "Numero Contacto must be positive")
    @Schema(description = "Type of contacto", example = "939733228")
    private String numero;
    
    @Schema(description = "User who performs the operation", example = "SYSTEM")
    private String usuario;
}
