package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.ContactoSolicitud;
import com.solicitud.dinet.domain.repository.ContactoSolicitudRepository;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.ContactoSolicitudMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories.ContactoSolicitudR2dbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContactoSolicitudRepositoryImpl implements ContactoSolicitudRepository{

    private final ContactoSolicitudR2dbcRepository r2dbcRepository;
    private final ContactoSolicitudMapper mapper;

    @Override
    public Mono<ContactoSolicitud> save(ContactoSolicitud contactoSolicitud) {
        log.debug("Guardar Contacto Solicitud: {} {}", contactoSolicitud.getContactoId(), contactoSolicitud.getSolicitudId());
        return r2dbcRepository.save(mapper.toEntity(contactoSolicitud))
                .map(mapper::toDomain)
                .doOnSuccess(saved -> log.debug("Contacto Solicitud guardada con id: {}", saved.getContactoSolicitudId()));
    }

    @Override
    public Mono<ContactoSolicitud> findById(UUID id) {
        log.debug("Encontrar Contacto Solicitud por id: {}", id);
        return r2dbcRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<ContactoSolicitud> findAll() {
        log.debug("Listar Contacto Solicitud");
        return r2dbcRepository.findAll().map(mapper::toDomain);
    }

    @Override
    public Flux<ContactoSolicitud> findByCodigo(UUID codigo) {
        return r2dbcRepository.findBySolicitudId(codigo).map(mapper::toDomain);
    }

}
