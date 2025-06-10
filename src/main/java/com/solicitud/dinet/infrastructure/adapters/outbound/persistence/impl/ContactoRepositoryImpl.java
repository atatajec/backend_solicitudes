package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.domain.repository.ContactoRepository;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers.ContactoMapper;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories.ContactoR2dbcRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContactoRepositoryImpl implements ContactoRepository{

    private final ContactoR2dbcRepository r2dbcRepository;
    private final ContactoMapper mapper;

    @Override
    public Mono<Contacto> save(Contacto contacto) {
        log.debug("Guardar contacto: {}", contacto.getNombre());
        return r2dbcRepository.save(mapper.toEntity(contacto))
                .map(mapper::toDomain)
                .doOnSuccess(saved -> log.debug("Contacto guardada con id: {}", saved.getContactoId()));
    }

    @Override
    public Mono<Contacto> findById(UUID id) {
        log.debug("Encontrar contacto por id: {}", id);
        return r2dbcRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Flux<Contacto> findAll() {
        log.debug("Listar contactos");
        return r2dbcRepository.findAll().map(mapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByCodigo(String codigo) {
        log.debug("Identificador Contacto: {}", codigo);
        return r2dbcRepository.existsByIdentificador(codigo);
    }

    @Override
    public Mono<Boolean> existsByNombre(String nombre) {
        log.debug("Nombre: {}", nombre);
        return r2dbcRepository.existsByNombre(nombre);
    }

    @Override
    public Mono<Contacto> findByNombre(String nombre) {
        log.debug("Nombre: {}", nombre);
        return r2dbcRepository.findByNombre(nombre);
    }

}
