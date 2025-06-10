package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca;

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
@Schema(description = "Request DTO for creating or updating a marca")
public class MarcaRequestDto {

    @NotNull(message = "Descripcion is required")
    @Positive(message = "Descripcion must be positive")
    @Schema(description = "Type of descripcion", example = "DINET")
    private String descripcion;

    @Schema(description = "User who performs the operation", example = "SYSTEM")
    private String usuario;
}
