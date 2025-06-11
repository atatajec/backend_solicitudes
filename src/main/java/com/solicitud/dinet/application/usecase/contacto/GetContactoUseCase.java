package com.solicitud.dinet.application.usecase.contacto;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.usecase.contactosolicitud.GetContactoSolicitudUseCase;
import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.domain.repository.ContactoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetContactoUseCase {
    private final ContactoRepository repository;
    private final GetContactoSolicitudUseCase contactoSolUseCase;

    public Mono<Contacto> findById(UUID id)
    {
        log.info("Listar Contacto por id: {}", id);
        return repository.findById(id)
                .doOnSuccess(obj -> {
                    if (obj != null) {
                        log.info("Contacto encontrada: {}", obj.getContactoId());
                    } else {
                        log.warn("Contacto no encontrada con id: {}", id);
                    }
                });
    }

    public Flux<Contacto> findAll() {
        log.info("Encontrar todas las Contactos");
        return repository.findAll()
                .doOnComplete(() -> log.info("Se recuperaron todas las Contactos"));
    }

    public Mono<Boolean> existByNombre(String nombre) {
        log.info("Encontrar todas las Contactos");
        return repository.existsByNombre(nombre)
                .doOnSuccess(obj -> log.info("Se recuperaron todas las Contactos"));
    }

    public Mono<Contacto> findByNombre(String nombre){
        log.info("Encontrar por Nombre");
        return repository.findByNombre(nombre)
                .doOnSuccess(obj -> log.info("Se recuperaron la persona"));
    }

    public Flux<Contacto> findByContactoPorSolicitud(UUID codigo)
    {
        return contactoSolUseCase.findByCodigo(codigo) // Flux<ContactoSolicitud>
        .flatMap(contactoSolicitud -> {
            UUID contactoId = contactoSolicitud.getContactoId();
            return findById(contactoId); // Mono<Contacto>
        });
    }

}
