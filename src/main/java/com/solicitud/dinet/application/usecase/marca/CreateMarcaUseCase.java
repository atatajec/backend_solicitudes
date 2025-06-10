package com.solicitud.dinet.application.usecase.marca;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.commands.marca.CreateMarcaCommand;
import com.solicitud.dinet.domain.models.Marca;
import com.solicitud.dinet.domain.repository.MarcaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateMarcaUseCase {

    public final MarcaRepository repository;

    public Mono<Marca> execute(CreateMarcaCommand command)
    {
        log.info("Crear nueva marca: {} {}", command.getDescripcion(), command.getUsuario());
        Marca marca = Marca.create(
            command.getDescripcion(),
            command.getUsuario()
        );
        return repository.save(marca)
                .doOnSuccess(m -> log.info("Marca creada: {}", 
                        m.getMarcaId()))
                .doOnError(error -> log.error("Error al crear marca", error));
    }
}
