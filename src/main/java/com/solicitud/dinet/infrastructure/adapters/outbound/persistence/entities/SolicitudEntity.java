package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities;

import java.time.LocalDate;
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
@Table("solicitudes")
public class SolicitudEntity {
    @Id
    @Column("solicitud_id")
    private UUID solicitudId;
    
    @Column("codigo_solicitud")
    private String codigoSolicitud;
    
    @Column("marca_id")
    private UUID marcaId;
    
    @Column("tipo_solicitud_id")
    private UUID tipoSolicitudId;
    
    @Column("fecha_envio")
    private LocalDate fechaEnvio;
    
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
