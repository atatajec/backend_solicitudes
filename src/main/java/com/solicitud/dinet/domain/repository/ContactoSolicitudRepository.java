package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.ContactoSolicitud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ContactoSolicitudRepository {
    Mono<ContactoSolicitud> save(ContactoSolicitud contactoSolicitud);
    Mono<ContactoSolicitud> findById(UUID id);
    Flux<ContactoSolicitud> findAll();
}
