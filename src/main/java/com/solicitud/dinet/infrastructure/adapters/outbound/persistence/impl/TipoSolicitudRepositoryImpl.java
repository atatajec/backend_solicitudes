package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.TipoSolicitud;
import com.solicitud.dinet.domain.repository.TipoSolicitudRepository;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.TipoSolicitudMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories.TipoSolicitudR2dbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TipoSolicitudRepositoryImpl implements TipoSolicitudRepository{
    
    private final TipoSolicitudR2dbcRepository r2dbcRepository;
    private final TipoSolicitudMapper mapper;

    @Override
    public Mono<TipoSolicitud> save(TipoSolicitud tipoSolicitud) {
        log.debug("Guardar Tipo Solicitud: {}", tipoSolicitud.getDescripcion());
        return r2dbcRepository.save(mapper.toEntity(tipoSolicitud))
                .map(mapper::toDomain)
                .doOnSuccess(saved -> log.debug("Contacto guardada con id: {}", saved.getTipoSolicitudId()));
    }

    @Override
    public Mono<TipoSolicitud> findById(UUID id) {
        log.debug("Encontrar Tipo de solicitud por id: {}", id);
        return r2dbcRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<TipoSolicitud> findAll() {
        log.debug("Encontrar todos los Tipo Solicitud");
        return r2dbcRepository.findAll()
                .map(mapper::toDomain);
    }

}
