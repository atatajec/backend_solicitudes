package com.solicitud.dinet.infrastructure.adapters.inbound.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solicitud.dinet.application.usecase.contacto.CreateContactoUseCase;
import com.solicitud.dinet.application.usecase.contacto.GetContactoUseCase;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.ContactoDtoMapper;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto.ContactoRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto.ContactoResponseDto;
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
@RequestMapping("/api/v1/contacto")
@RequiredArgsConstructor
@Tag(name = "Contactos")
@CrossOrigin(origins = "*")
public class ContactoController {

    private final CreateContactoUseCase createContactoUseCase;
    private final GetContactoUseCase getContactoUseCase;
    private final ContactoDtoMapper dtoMapper;

    @PostMapping
    @Operation(summary = "Crear contacto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Marca created successfully",
                content = @Content(schema = @Schema(implementation = ContactoResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ResponseApi<ContactoResponseDto>>> createContacto(
            @Valid @RequestBody ContactoRequestDto requestDto) {
        log.info("Creating new Contacto: {} {}", requestDto.getNombre(), requestDto.getNumero());
        
        return createContactoUseCase.execute(dtoMapper.toCreateCommand(requestDto))
                .map(dtoMapper::toResponseDto)
                .map(responseDto -> {
                    ResponseApi<ContactoResponseDto> apiResponse = ResponseApi.<ContactoResponseDto>builder()
                        .success(true)
                        .message("Contacto creado exitosamente")
                        .data(responseDto)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                })
                .doOnSuccess(r -> log.info("Marca created successfully"))
                .doOnError(e -> log.error("Error creating contacto", e));
    }

    @GetMapping
    @Operation(summary = "Listar contactos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contactos retrieved successfully")
    })
    public Mono<ResponseEntity<ResponseApi<List<ContactoResponseDto>>>> getAllContacto()
    {
        return getContactoUseCase.findAll()
                .map(dtoMapper::toResponseDto)
                .collectList()
                .map(lista -> {
                    ResponseApi<List<ContactoResponseDto>> apiResponse = ResponseApi.<List<ContactoResponseDto>>builder()
                        .success(true)
                        .message("Listado de contactos")
                        .data(lista)
                        .build();

                    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);                    
                });
    }


}
