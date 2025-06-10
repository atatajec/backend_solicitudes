package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class SolicitudResponseDto {
    @Schema(description = "Solicitud ID", example = "1")
    private UUID solicitudId;
    
    @Schema(description = "Auto-generated solicitud code", example = "SOL-ABC12345")
    private String codigoSolicitud;
    
    @Schema(description = "ID of the marca", example = "1")
    private String marcaId;
    
    @Schema(description = "Type of solicitud", example = "1")
    private String tipoSolicitudId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Date when solicitud was sent", example = "2024-01-15")
    private LocalDate fechaEnvio;
    
    @Schema(description = "Status of the solicitud", example = "ACTIVO")
    private String estado;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Creation timestamp")
    private LocalDateTime fechaCreacion;
    
    @Schema(description = "User who created the solicitud", example = "admin")
    private String usuarioCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Last modification timestamp")
    private LocalDateTime fechaModificacion;
    
    @Schema(description = "User who last modified the solicitud", example = "admin")
    private String usuarioModificacion;
}
