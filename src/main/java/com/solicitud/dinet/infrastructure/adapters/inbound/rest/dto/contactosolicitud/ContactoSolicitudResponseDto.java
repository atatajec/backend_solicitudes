package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud;

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
@Schema(description = "Response DTO for Contacto Solicitud")
public class ContactoSolicitudResponseDto {

    @Schema(description = "Solicitud ID", example = "UUID")
    private UUID solicitudId;

    @Schema(description = "Contacto ID", example = "UUID")
    private UUID contactoId;

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
