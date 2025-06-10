package com.solicitud.dinet.application.commands.marca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMarcaCommand {
    private String descripcion;
    private String usuario;
}
