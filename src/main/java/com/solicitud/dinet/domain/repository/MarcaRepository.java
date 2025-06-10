package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Marca;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MarcaRepository {
    Mono<Marca> save(Marca marca);
    Mono<Marca> findById(UUID id);
    Flux<Marca> findAll();
}
