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

import com.solicitud.dinet.application.usecase.tiposolicitud.CreateTipoSolicitudUseCase;
import com.solicitud.dinet.application.usecase.tiposolicitud.GetTipoSolicitudUseCase;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.tiposolicitud.TipoSolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.tiposolicitud.TipoSolicitudResponseDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.TipoSolicitudDtoMapper;
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
@RequestMapping("/api/v1/tiposolicitud")
@RequiredArgsConstructor
@Tag(name = "Tipo Solicitud")
@CrossOrigin(origins = "*")
public class TipoSolicitudController {

    private final CreateTipoSolicitudUseCase createTipoSolicitudUseCase;
    private final GetTipoSolicitudUseCase getTipoSolicitudUseCase;
    private final TipoSolicitudDtoMapper dtoMapper;

    @PostMapping
    @Operation(summary = "Crear TipoSolicitud")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "TipoSolicitud created successfully",
                content = @Content(schema = @Schema(implementation = TipoSolicitudResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ResponseApi<TipoSolicitudResponseDto>>> createTipoSolicitud(
            @Valid @RequestBody TipoSolicitudRequestDto requestDto) {
        log.info("Creating new Tipo Solicitud: {}", requestDto.getDescripcion());
        
        return createTipoSolicitudUseCase.execute(dtoMapper.toCreateCommand(requestDto))
                .map(dtoMapper::toResponseDto)
                .map(responseDto -> {
                    ResponseApi<TipoSolicitudResponseDto> apiResponse = ResponseApi.<TipoSolicitudResponseDto>builder()
                        .success(true)
                        .message("Tipo Solicitud creada exitosamente")
                        .data(responseDto)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                })
                .doOnSuccess(r -> log.info("Tipo Solicitud created successfully"))
                .doOnError(e -> log.error("Error creating Marca", e));
    }

    @GetMapping
    @Operation(summary = "Listar TipoSolicitud")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "TipoSolicitud retrieved successfully")
    })
    public Mono<ResponseEntity<ResponseApi<List<TipoSolicitudResponseDto>>>> getAllTipoSolicitud()
    {
        return getTipoSolicitudUseCase.findAll()
                .map(dtoMapper::toResponseDto)
                .collectList()
                .map(lista -> {
                    ResponseApi<List<TipoSolicitudResponseDto>> apiResponse = ResponseApi.<List<TipoSolicitudResponseDto>>builder()
                        .success(true)
                        .message("Listado de TipoSolicituds")
                        .data(lista)
                        .build();

                    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);                    
                });
    }

}
