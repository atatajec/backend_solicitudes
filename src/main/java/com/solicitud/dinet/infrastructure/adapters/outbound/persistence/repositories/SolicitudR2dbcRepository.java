package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudEntity;

import reactor.core.publisher.Mono;

public interface SolicitudR2dbcRepository extends R2dbcRepository<SolicitudEntity, UUID> {
    Mono<SolicitudEntity> findByCodigoSolicitud(String codigoSolicitud);
    Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud);
}
