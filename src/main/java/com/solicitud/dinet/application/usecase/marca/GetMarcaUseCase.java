package com.solicitud.dinet.application.usecase.marca;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.domain.models.Marca;
import com.solicitud.dinet.domain.repository.MarcaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMarcaUseCase {

    private final MarcaRepository repository;

    public Mono<Marca> findById(UUID id)
    {
        log.info("Listar Marca por id: {}", id);
        return repository.findById(id)
                .doOnSuccess(obj -> {
                    if (obj != null) {
                        log.info("Marca encontrada: {}", obj.getMarcaId());
                    } else {
                        log.warn("Marca no encontrada con id: {}", id);
                    }
                });
    }

    public Flux<Marca> findAll() {
        log.info("Encontrar todas las marcas");
        return repository.findAll()
                .doOnComplete(() -> log.info("Se recuperaron todas las marcas"));
    }

}
