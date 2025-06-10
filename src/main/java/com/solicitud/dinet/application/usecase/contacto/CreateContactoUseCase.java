package com.solicitud.dinet.application.usecase.contacto;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.solicitud.dinet.application.commands.contacto.CreateContactoCommand;
import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.domain.repository.ContactoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateContactoUseCase {
    public final ContactoRepository repository;

    public Mono<Contacto> execute(CreateContactoCommand command)
    {
        log.info("Crear nueva contacto: {} {}", command.getNombre(), command.getNumero());

        return generateCodigo()
                .flatMap(codigo -> {
                    Contacto contacto = Contacto.create(
                        codigo,
                        command.getNombre(),
                        command.getNumero(),
                        command.getUsuario()
                    );
                    return repository.save(contacto);
                })
                .doOnSuccess(m -> log.info("Contacto creada: {}", 
                        m.getIdentificador()))
                .doOnError(error -> log.error("Error al crear contacto", error));                
    }

    private Mono<String> generateCodigo() {
        String codigo = "ID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return repository.existsByCodigo(codigo)
                .flatMap(exists -> exists ? generateCodigo() : Mono.just(codigo));
    }
}
