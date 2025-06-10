package com.solicitud.dinet.application.usecase.contactosolicitud;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.domain.models.ContactoSolicitud;
import com.solicitud.dinet.domain.repository.ContactoSolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetContactoSolicitudUseCase {

    private final ContactoSolicitudRepository repository;

    public Mono<ContactoSolicitud> findById(UUID id)
    {
        log.info("Listar Contacto por id: {}", id);
        return repository.findById(id)
                .doOnSuccess(obj -> {
                    if (obj != null) {
                        log.info("Contacto solicitud encontrada: {}", obj.getContactoSolicitudId());
                    } else {
                        log.warn("Contacto solicitud no encontrada con id: {}", id);
                    }
                });
    }

    public Flux<ContactoSolicitud> findAll() {
        log.info("Encontrar todas los Contactos solicitud");
        return repository.findAll()
                .doOnComplete(() -> log.info("Se recuperaron todas los Contactos solicitud"));
    }

}
