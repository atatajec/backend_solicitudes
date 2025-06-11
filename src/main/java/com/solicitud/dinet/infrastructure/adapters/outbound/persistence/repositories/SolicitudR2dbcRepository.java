package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudDetalleEntity;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudR2dbcRepository extends ReactiveCrudRepository<SolicitudEntity, UUID> {
    Mono<SolicitudEntity> findByCodigoSolicitud(String codigoSolicitud);
    Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud);

    @Query("""
            SELECT a.codigo_solicitud, b.descripcion AS marca, a.fecha_envio, c.descripcion AS tipo,
            e.nombre_contacto, e.numero_contacto
            FROM public.solicitudes a
            INNER JOIN public.marcas b ON a.marca_id = b.marca_id
            INNER JOIN public.tipo_solicitud c ON a.tipo_solicitud_id = c.tipo_solicitud_id
            INNER JOIN public.contactos_solicitud d ON a.solicitud_id = d.solicitud_id AND d.tipo = 1
            INNER JOIN public.contactos e ON d.contacto_id = e.contacto_id
            WHERE (:marca IS NULL OR b.descripcion ILIKE :marca)
            AND (:tipoSolicitud IS NULL OR c.descripcion ILIKE :tipoSolicitud)
            AND a.fecha_envio BETWEEN :fechaEnvioDesde AND :fechaEnvioHasta
            """)
    Flux<SolicitudDetalleEntity> findBySolicitudFiltro(String tipoSolicitud, String marca, LocalDate fechaEnvioDesde, LocalDate fechaEnvioHasta);
}
