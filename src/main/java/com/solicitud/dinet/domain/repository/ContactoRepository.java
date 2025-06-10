package com.solicitud.dinet.domain.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.solicitud.dinet.domain.models.Contacto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ContactoRepository {
    Mono<Contacto> save(Contacto contacto);    
    Mono<Contacto> findById(UUID id);    
    Mono<Boolean> existsByNombre(String nombre);    
    Flux<Contacto> findAll();
    Mono<Boolean> existsByCodigo(String codigo);
    Mono<Contacto> findByNombre(String nombre);
}
