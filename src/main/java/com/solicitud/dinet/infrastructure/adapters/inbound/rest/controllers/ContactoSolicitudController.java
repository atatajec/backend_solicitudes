package com.solicitud.dinet.infrastructure.adapters.inbound.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solicitud.dinet.application.usecase.contactosolicitud.CreateContactoSolicitudUseCase;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud.ContactoSolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud.ContactoSolicitudResponseDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.ContactoSolicitudDtoMapper;
import com.solicitud.dinet.interfaces.http.ResponseApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/contactosolicitud")
@RequiredArgsConstructor
@Tag(name = "Contacto Solicitud")
@CrossOrigin(origins = "*")
public class ContactoSolicitudController {

    private final CreateContactoSolicitudUseCase createUseCase;
    private final ContactoSolicitudDtoMapper dtoMapper;

    @PostMapping
    @Operation(summary = "Crear solicitud contacto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud created successfully",
                content = @Content(schema = @Schema(implementation = ContactoSolicitudResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ResponseApi<ContactoSolicitudResponseDto>>> createSolicitud(
            @Valid @RequestBody ContactoSolicitudRequestDto requestDto) {
        log.info("Creating new solicitud contacto", requestDto.getContactoId());
        
        return createUseCase.execute(dtoMapper.toCreateCommand(requestDto))
                .map(dtoMapper::toResponseDto)
                .map(responseDto -> {
                    ResponseApi<ContactoSolicitudResponseDto> apiResponse = ResponseApi.<ContactoSolicitudResponseDto>builder()
                        .success(true)
                        .message("Solicitud contacto creada exitosamente")
                        .data(responseDto)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                })
                .doOnSuccess(r -> log.info("Solicitud contacto created successfully"))
                .doOnError(e -> log.error("Error creating solicitud", e));
    }

}
