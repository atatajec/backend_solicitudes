package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Marca;
import com.solicitud.dinet.domain.repository.MarcaRepository;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.MarcaMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories.MarcaR2dbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MarcaRepositoryImpl implements MarcaRepository {

    private final MarcaR2dbcRepository r2dbcRepository;
    private final MarcaMapper mapper;

    @Override
    public Mono<Marca> save(Marca marca) {
        log.debug("Guardar marca: {}", marca.getDescripcion());
        return r2dbcRepository.save(mapper.toEntity(marca))
                .map(mapper::toDomain)
                .doOnSuccess(saved -> log.debug("Marca guardada con id: {}", saved.getMarcaId()));
    }

    @Override
    public Mono<Marca> findById(UUID id) {
        log.debug("Encontrar marca por id: {}", id);
        return r2dbcRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Marca> findAll() {
        log.debug("Listar marcas");
        return r2dbcRepository.findAll().map(mapper::toDomain);
    }

}
