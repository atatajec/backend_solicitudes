package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto;

import java.time.LocalDateTime;
import java.util.UUID;

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
@Schema(description = "Response DTO for solicitud")
public class ContactoResponseDto {

    @Positive(message = "Contacto ID must be positive")
    @Schema(description = "ID of the Contacto", example = "UUID")
    private UUID contactoId;

    @NotNull(message = "Identificador is required")
    @Positive(message = "Identificador must be positive")
    @Schema(description = "Type of Contacto", example = "CODIGO")
    private String identificador;
    
    @NotNull(message = "Nombre is required")
    @Positive(message = "Nombre must be positive")
    @Schema(description = "Type of Contacto", example = "Alexis Tataje")
    private String nombre;

    @NotNull(message = "Numero Contacto is required")
    @Positive(message = "Numero Contacto must be positive")
    @Schema(description = "Type of Numero Contacto", example = "939733228")
    private String numero;

    @Schema(description = "Status of the Contacto", example = "ACTIVO")
    private String estado;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Creation timestamp")
    private LocalDateTime fechaCreacion;
    
    @Schema(description = "User who created the marca", example = "SYSTEM")
    private String usuarioCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Last modification timestamp")
    private LocalDateTime fechaModificacion;
    
    @Schema(description = "User who last modified the marca", example = "SYSTEM")
    private String usuarioModificacion;

}
