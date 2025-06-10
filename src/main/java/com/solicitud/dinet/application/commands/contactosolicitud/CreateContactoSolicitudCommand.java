package com.solicitud.dinet.application.commands.contactosolicitud;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactoSolicitudCommand {
    private UUID solicitudId;
    private UUID contactoId;
    private String usuario;
}
