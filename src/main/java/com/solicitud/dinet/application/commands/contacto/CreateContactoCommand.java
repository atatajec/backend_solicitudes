package com.solicitud.dinet.application.commands.contacto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactoCommand {
    private String nombre;
    private String numero;
    private String usuario;
}
