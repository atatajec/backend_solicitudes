package com.solicitud.dinet.application.usecase.solicitud;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.domain.models.SolicitudDetalle;
import com.solicitud.dinet.domain.models.SolicitudFiltro;
import com.solicitud.dinet.domain.repository.SolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetSolicitudesUseCase {

    private final SolicitudRepository solicitudRepository;
    
    public Flux<SolicitudDetalle> execute(SolicitudFiltro filtro) {
        log.info("Buscando solicitudes con filtros: {}", filtro);
        return solicitudRepository.buscarSolicitudesConFiltros(filtro)
                .doOnNext(solicitud -> log.debug("Solicitud encontrada: {}", solicitud.getCodigosolicitud()))
                .doOnComplete(() -> log.info("Búsqueda completada"));
    }
    
    public Mono<SolicitudDetalle> buscarPorId(UUID solicitudId) {
        log.info("Buscando solicitud por ID: {}", solicitudId);
        return solicitudRepository.buscarSolicitudPorId(solicitudId)
                .doOnNext(solicitud -> log.debug("Solicitud encontrada: {}", solicitud))
                .switchIfEmpty(Mono.fromRunnable(() -> log.warn("No se encontró solicitud con ID: {}", solicitudId))
                        .then(Mono.empty()));
    }
}
