package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Solicitud;
import com.solicitud.dinet.domain.models.SolicitudDetalle;
import com.solicitud.dinet.domain.models.SolicitudFiltro;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SolicitudRepository {
    Mono<Solicitud> save(Solicitud solicitud);
    Mono<Solicitud> findById(UUID id);
    Flux<Solicitud> findAll();
    Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud);
    Flux<SolicitudDetalle> buscarSolicitudesConFiltros(SolicitudFiltro filtro);
    Mono<SolicitudDetalle> buscarSolicitudPorId(UUID solicitudId);
}
