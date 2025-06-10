package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.TipoSolicitud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TipoSolicitudRepository {
    Mono<TipoSolicitud> save(TipoSolicitud tipoSolicitud);
    Mono<TipoSolicitud> findById(UUID id);
    Flux<TipoSolicitud> findAll();
}
