package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.ContactoEntity;

import reactor.core.publisher.Mono;

public interface ContactoR2dbcRepository extends R2dbcRepository<ContactoEntity, UUID> {
    Mono<Boolean> existsByIdentificador(String codigo);
    Mono<Boolean> existsByNombre(String nombre);
    Mono<Contacto> findByNombre(String nombre);
}
