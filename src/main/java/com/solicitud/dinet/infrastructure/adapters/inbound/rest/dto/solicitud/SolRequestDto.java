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
@Schema(description = "Request DTO crear solicitud")
public class SolRequestDto {

    @Positive(message = "Marca ID must be positive")
    @Schema(description = "Marca ID", example = "UUID")
    private String marcaId;
    
    @NotNull(message = "Tipo solicitud is required")
    @Positive(message = "Tipo solicitud must be positive")
    @Schema(description = "Type of solicitud", example = "UUID")
    private String tipoSolicitudId;
    
    @NotNull(message = "Fecha envio is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date when solicitud was sent", example = "2024-01-15")
    private LocalDate fechaEnvio;

    @NotNull(message = "Nombre is required")
    @Positive(message = "Nombre must be positive")
    @Schema(description = "Type of Contacto", example = "Alexis Tataje")
    private String nombre;

    @NotNull(message = "Numero is required")
    @Positive(message = "Numero must be positive")
    @Schema(description = "Type of Numero", example = "939733228")
    private String numero;
    
    @Schema(description = "User who performs the operation", example = "SYSTEM")
    private String usuario;
}
