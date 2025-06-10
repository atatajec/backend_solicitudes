package com.solicitud.dinet.infrastructure.adapters.inbound.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solicitud.dinet.application.usecase.marca.CreateMarcaUseCase;
import com.solicitud.dinet.application.usecase.marca.GetMarcaUseCase;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca.MarcaRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca.MarcaResponseDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.MarcaDtoMapper;
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
@RequestMapping("/api/v1/marca")
@RequiredArgsConstructor
@Tag(name = "Marca")
public class MarcaController {

    private final CreateMarcaUseCase createMarcaUseCase;
    private final GetMarcaUseCase getMarcaUseCase;
    private final MarcaDtoMapper dtoMapper;

    @PostMapping
    @Operation(summary = "Crear marca")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Marca created successfully",
                content = @Content(schema = @Schema(implementation = MarcaResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ResponseApi<MarcaResponseDto>>> createMarca(
            @Valid @RequestBody MarcaRequestDto requestDto) {
        log.info("Creating new Marca for marca: {}", requestDto.getDescripcion());
        
        return createMarcaUseCase.execute(dtoMapper.toCreateCommand(requestDto))
                .map(dtoMapper::toResponseDto)
                .map(responseDto -> {
                    ResponseApi<MarcaResponseDto> apiResponse = ResponseApi.<MarcaResponseDto>builder()
                        .success(true)
                        .message("Marca creada exitosamente")
                        .data(responseDto)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                })
                .doOnSuccess(r -> log.info("Marca created successfully"))
                .doOnError(e -> log.error("Error creating Marca", e));
    }

    @GetMapping
    @Operation(summary = "Listar marcas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marcas retrieved successfully")
    })
    public Mono<ResponseEntity<ResponseApi<List<MarcaResponseDto>>>> getAllMarca()
    {
        return getMarcaUseCase.findAll()
                .map(dtoMapper::toResponseDto)
                .collectList()
                .map(lista -> {
                    ResponseApi<List<MarcaResponseDto>> apiResponse = ResponseApi.<List<MarcaResponseDto>>builder()
                        .success(true)
                        .message("Listado de marcas")
                        .data(lista)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);                    
                });
    }
}
