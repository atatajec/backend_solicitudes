package com.solicitud.dinet.application.usecase.contactosolicitud;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.commands.contactosolicitud.CreateContactoSolicitudCommand;
import com.solicitud.dinet.domain.models.ContactoSolicitud;
import com.solicitud.dinet.domain.repository.ContactoSolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateContactoSolicitudUseCase {
    public final ContactoSolicitudRepository repository;

    public Mono<ContactoSolicitud> execute(CreateContactoSolicitudCommand command)
    {
        log.info("Crear nuevo contacto solicitud: {} {}", command.getSolicitudId(), command.getContactoId());
        ContactoSolicitud contacto = ContactoSolicitud.create(
            command.getSolicitudId(),
            command.getContactoId(),
            command.getUsuario()
        );
        return repository.save(contacto)
                .doOnSuccess(m -> log.info("Contacto solicitud creada: {}", 
                        m.getContactoSolicitudId()))
                .doOnError(error -> log.error("Error al crear contacto solicitud", error));
    }
}
