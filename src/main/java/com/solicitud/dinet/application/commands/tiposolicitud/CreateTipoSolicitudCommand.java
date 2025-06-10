package com.solicitud.dinet.application.commands.tiposolicitud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTipoSolicitudCommand {
    private String descripcion;
    private String usuario;
}
