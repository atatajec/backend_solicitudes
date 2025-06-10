package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca;

import java.time.LocalDateTime;

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
@Schema(description = "Response DTO for marca")
public class MarcaResponseDto {
    @Positive(message = "Marca ID must be positive")
    @Schema(description = "ID of the marca", example = "UUID")
    private String marcaId;
    
    @NotNull(message = "Marca is required")
    @Positive(message = "Marca must be positive")
    @Schema(description = "Type of marca", example = "DINET")
    private String descripcion;

    @Schema(description = "Status of the marca", example = "ACTIVO")
    private String estado;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Creation timestamp")
    private LocalDateTime fechaCreacion;
    
    @Schema(description = "User who created the marca", example = "admin")
    private String usuarioCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Last modification timestamp")
    private LocalDateTime fechaModificacion;
    
    @Schema(description = "User who last modified the marca", example = "admin")
    private String usuarioModificacion;
}
