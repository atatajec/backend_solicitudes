package com.solicitud.dinet.application.usecase.tiposolicitud;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.domain.models.TipoSolicitud;
import com.solicitud.dinet.domain.repository.TipoSolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetTipoSolicitudUseCase {

    private final TipoSolicitudRepository repository;

    public Mono<TipoSolicitud> findById(UUID id)
    {
        log.info("Listar TipoSolicitud por id: {}", id);
        return repository.findById(id)
                .doOnSuccess(obj -> {
                    if (obj != null) {
                        log.info("TipoSolicitud encontrada: {}", obj.getTipoSolicitudId());
                    } else {
                        log.warn("TipoSolicitud no encontrada con id: {}", id);
                    }
                });
    }

    public Flux<TipoSolicitud> findAll() {
        log.info("Encontrar todas las TipoSolicituds");
        return repository.findAll()
                .doOnComplete(() -> log.info("Se recuperaron todas las Tipo Solicitud"));
    }

}
