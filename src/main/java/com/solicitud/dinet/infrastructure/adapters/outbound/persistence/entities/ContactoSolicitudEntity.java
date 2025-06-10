package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("contactos_solicitud")
public class ContactoSolicitudEntity {
    @Id
    @Column("contacto_solicitud_id")
    private UUID contactoSolicitudId;

    @Column("solicitud_id")
    private UUID solicitudId;

    @Column("contacto_id")
    private UUID contactoId;

    @Column("estado")
    private String estado;
    
    @Column("fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column("usuario_creacion")
    private String usuarioCreacion;
    
    @Column("fecha_modificacion")
    private LocalDateTime fechaModificacion;
    
    @Column("usuario_modificacion")
    private String usuarioModificacion;

}
