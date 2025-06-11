package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Solicitud;
import com.solicitud.dinet.domain.models.SolicitudDetalle;
import com.solicitud.dinet.domain.models.SolicitudFiltro;
import com.solicitud.dinet.domain.repository.SolicitudRepository;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.SolicitudDetalleMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.SolicitudMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories.SolicitudR2dbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SolicitudRepositoryImpl implements SolicitudRepository {
    
    private final SolicitudR2dbcRepository r2dbcRepository;
    private final SolicitudMapper mapper;
    private final SolicitudDetalleMapper mapperDet;


    @Override
    public Mono<Solicitud> save(Solicitud solicitud) {
        log.debug("Guardar solicitud: {}", solicitud.getCodigoSolicitud());
        return r2dbcRepository.save(mapper.toEntity(solicitud))
                .map(mapper::toDomain)
                .doOnSuccess(saved -> log.debug("Solicitud guardada con id: {}", saved.getSolicitudId()));
    }

    @Override
    public Mono<Solicitud> findById(UUID id) {
        log.debug("Encontrar solicitud por id: {}", id);
        return r2dbcRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Solicitud> findAll() {
        log.debug("Encontrar todas las solicitudes");
        return r2dbcRepository.findAll()
                .map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByCodigoSolicitud(String codigoSolicitud) {
        log.debug("Comprobando si existe por codigo: {}", codigoSolicitud);
        return r2dbcRepository.existsByCodigoSolicitud(codigoSolicitud);
    }

    @Override
    public Flux<SolicitudDetalle> buscarSolicitudesConFiltros(SolicitudFiltro filtro) {
        return r2dbcRepository.findBySolicitudFiltro(filtro.getTipo(), filtro.getMarca(), filtro.getFechaDesde(), filtro.getFechaHasta())
                    .map(mapperDet::toDomain);
    }

    @Override
    public Mono<SolicitudDetalle> buscarSolicitudPorId(UUID solicitudId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarSolicitudPorId'");
    }

}
