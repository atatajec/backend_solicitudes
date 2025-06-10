package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.tiposolicitud;

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
@Schema(description = "Response DTO for Tipo Sol.")
public class TipoSolicitudResponseDto {
    @Positive(message = "Tipo Sol. ID must be positive")
    @Schema(description = "ID of the Tipo Sol.", example = "UUID")
    private String tipoSolicitudId;
    
    @NotNull(message = "Tipo Sol. is required")
    @Positive(message = "Tipo Sol. must be positive")
    @Schema(description = "Type of Tipo Sol.", example = "TIPO-1")
    private String descripcion;

    @Schema(description = "Status of the Tipo Sol.", example = "ACTIVO")
    private String estado;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Creation timestamp")
    private LocalDateTime fechaCreacion;
    
    @Schema(description = "User who created the Tipo Sol.", example = "admin")
    private String usuarioCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Last modification timestamp")
    private LocalDateTime fechaModificacion;
    
    @Schema(description = "User who last modified the Tipo Sol.", example = "admin")
    private String usuarioModificacion;   

}
