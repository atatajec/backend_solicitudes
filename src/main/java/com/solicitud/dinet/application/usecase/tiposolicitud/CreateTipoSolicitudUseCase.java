package com.solicitud.dinet.application.usecase.tiposolicitud;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.commands.tiposolicitud.CreateTipoSolicitudCommand;
import com.solicitud.dinet.domain.models.TipoSolicitud;
import com.solicitud.dinet.domain.repository.TipoSolicitudRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTipoSolicitudUseCase {

    public final TipoSolicitudRepository repository;

    public Mono<TipoSolicitud> execute(CreateTipoSolicitudCommand command)
    {
        log.info("Crear nueva Tipo Solicitud solicitud: {}", command.getDescripcion());
        TipoSolicitud tipoSolicitud = TipoSolicitud.create(
            command.getDescripcion(),
            command.getUsuario()
        );
        return repository.save(tipoSolicitud)
                .doOnSuccess(m -> log.info("Tipo Solicitud solicitud creada: {}", 
                        m.getTipoSolicitudId()))
                .doOnError(error -> log.error("Error al crear Tipo Solicitud solicitud", error));
    }

}
