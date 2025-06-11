package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudDetalleEntity;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudR2dbcRepository extends ReactiveCrudRepository<SolicitudEntity, UUID> {
    Mono<SolicitudEntity> findByCodigoSolicitud(String codigoSolicitud);
    Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud);

    @Query("""
            SELECT 
                a.codigo_solicitud AS codigosolicitud, 
                b.descripcion AS marca, 
                a.fecha_envio AS fechaenvio, 
                c.descripcion AS tipo,
                e.nombre_contacto AS nombrecontacto, 
                e.numero_contacto AS numerocontacto
            FROM public.solicitudes a
            INNER JOIN public.marcas b ON a.marca_id = b.marca_id
            INNER JOIN public.tipo_solicitud c ON a.tipo_solicitud_id = c.tipo_solicitud_id
            INNER JOIN public.contactos_solicitud d ON a.solicitud_id = d.solicitud_id AND d.tipo = 1
            INNER JOIN public.contactos e ON d.contacto_id = e.contacto_id
            WHERE (:marca IS NULL OR b.descripcion LIKE :marca)
            AND (:tipoSolicitud IS NULL OR c.descripcion LIKE :tipoSolicitud)
            AND a.fecha_envio BETWEEN :fechaEnvioDesde AND :fechaEnvioHasta
            """)
    Flux<SolicitudDetalleEntity> findBySolicitudFiltro(
        @Param("tipoSolicitud") String tipoSolicitud,
        @Param("marca") String marca,
        @Param("fechaEnvioDesde") LocalDate fechaEnvioDesde,
        @Param("fechaEnvioHasta") LocalDate fechaEnvioHasta);

    @Query("""
            SELECT 
                a.codigo_solicitud AS codigosolicitud, 
                b.descripcion AS marca, 
                a.fecha_envio AS fechaenvio, 
                c.descripcion AS tipo,
                e.nombre_contacto AS nombrecontacto, 
                e.numero_contacto AS numerocontacto
            FROM public.solicitudes a
            INNER JOIN public.marcas b ON a.marca_id = b.marca_id
            INNER JOIN public.tipo_solicitud c ON a.tipo_solicitud_id = c.tipo_solicitud_id
            INNER JOIN public.contactos_solicitud d ON a.solicitud_id = d.solicitud_id AND d.tipo = 1
            INNER JOIN public.contactos e ON d.contacto_id = e.contacto_id
            WHERE a.codigo_solicitud = :codigo
            """)
    Mono<SolicitudDetalleEntity> findBySolicitudCodigo(
        @Param("codigo") String codigo);

    @Query("""
            SELECT b.nombre_contacto AS nombrecontacto, 
                b.numero_contacto AS numerocontacto 
            FROM public.contactos_solicitud a
            INNER JOIN public.contactos b ON a.contacto_id = b.contacto_id
            INNER JOIN public.solicitudes c ON a.solicitud_id = c.solicitud_id 
            WHERE c.codigo_solicitud = :codigo
            """)
    Flux<SolicitudDetalleEntity> findByContactoPorCodigo(
        @Param("codigo") String codigo);
}
