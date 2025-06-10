package com.solicitud.dinet.application.commands.solicitud;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSolicitudCommand {
    private UUID marcaId;
    private UUID tipoSolicitudId;
    private LocalDate fechaEnvio;
    private String nombre;
    private String numero;
    private String usuario;
}
