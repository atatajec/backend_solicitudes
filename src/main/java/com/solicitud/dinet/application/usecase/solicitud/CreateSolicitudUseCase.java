package com.solicitud.dinet.application.usecase.solicitud;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.commands.contacto.CreateContactoCommand;
import com.solicitud.dinet.application.commands.contactosolicitud.CreateContactoSolicitudCommand;
import com.solicitud.dinet.application.commands.solicitud.CreateSolicitudCommand;
import com.solicitud.dinet.application.usecase.contacto.CreateContactoUseCase;
import com.solicitud.dinet.application.usecase.contacto.GetContactoUseCase;
import com.solicitud.dinet.application.usecase.contactosolicitud.CreateContactoSolicitudUseCase;
import com.solicitud.dinet.domain.models.Solicitud;
import com.solicitud.dinet.domain.repository.SolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSolicitudUseCase {

    private final SolicitudRepository repository;
    private final GetContactoUseCase getContacto;
    private final CreateContactoUseCase createContacto;
    private final CreateContactoSolicitudUseCase createContactoSolicitudUseCase;

    public Mono<Solicitud> execute(CreateSolicitudCommand command) {
        log.info("Crear nueva solicitud por marca: {}, tipo: {}", 
                command.getMarcaId(), command.getTipoSolicitudId());

        log.info("MarcaID: {}, tipo: {}",command.getMarcaId());
        log.info("TipoSolicitudID: {}, tipo: {}",command.getTipoSolicitudId());
        log.info("Fecha Envio: {}, tipo: {}",command.getFechaEnvio());
        
        return generateCodigoSolicitud()
                .flatMap(codigo -> {
                    Solicitud solicitud = Solicitud.create(
                            codigo,
                            command.getMarcaId(),
                            command.getTipoSolicitudId(),
                            command.getFechaEnvio(),
                            command.getUsuario()
                    );
                    return repository.save(solicitud);
                })
                .flatMap(solicitudGuardada -> {
                        CreateContactoCommand contactoCommand = 
                                new CreateContactoCommand(command.getNombre(), command.getNumero(), command.getUsuario());

                        return getContacto.findByNombre(command.getNombre())
                                .switchIfEmpty(
                                        createContacto.execute(contactoCommand) // Crear si no existe
                                )
                                .flatMap(contacto -> {
                                        CreateContactoSolicitudCommand relacionCommand =
                                                new CreateContactoSolicitudCommand(1, solicitudGuardada.getSolicitudId(), contacto.getContactoId(), command.getUsuario());

                                        return createContactoSolicitudUseCase.execute(relacionCommand)
                                                .thenReturn(solicitudGuardada);
                                });
                })
                .doOnSuccess(solicitud -> log.info("Solicitud creada con código: {}", 
                        solicitud.getCodigoSolicitud()))
                .doOnError(error -> log.error("Error al crear solicitud", error));
    }
    
    private Mono<String> generateCodigoSolicitud() {
        String codigo = "SOL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return repository.existsByCodigoSolicitud(codigo)
                .flatMap(exists -> exists ? generateCodigoSolicitud() : Mono.just(codigo));
    }
}
