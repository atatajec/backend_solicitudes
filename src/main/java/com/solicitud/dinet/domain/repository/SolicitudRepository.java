package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Solicitud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SolicitudRepository {
    Mono<Solicitud> save(Solicitud solicitud);
    Mono<Solicitud> findById(UUID id);
    Flux<Solicitud> findAll();
    Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud);
}
