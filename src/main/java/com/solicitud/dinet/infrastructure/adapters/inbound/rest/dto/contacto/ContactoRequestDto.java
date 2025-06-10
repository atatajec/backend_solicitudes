package com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto;

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
@Schema(description = "Request DTO for creating or updating a contacto")
public class ContactoRequestDto {

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
