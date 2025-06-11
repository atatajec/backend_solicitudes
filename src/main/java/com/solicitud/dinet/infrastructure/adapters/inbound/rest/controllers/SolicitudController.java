package com.solicitud.dinet.infrastructure.adapters.inbound.rest.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solicitud.dinet.application.usecase.solicitud.CreateSolicitudUseCase;
import com.solicitud.dinet.application.usecase.solicitud.GetSolicitudesUseCase;
import com.solicitud.dinet.domain.models.SolicitudFiltro;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudDetalleResponseDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudResponseDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.SolicitudDetalleDtoMapper;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers.SolicitudDtoMapper;

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
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
@Tag(name = "Solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    private final CreateSolicitudUseCase createSolicitudUseCase;
    private final SolicitudDtoMapper dtoMapper;
    private final GetSolicitudesUseCase getSolicitudUseCase;
    private final SolicitudDetalleDtoMapper dtoDetMapper;

    @PostMapping
    @Operation(summary = "Crear solicitud")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud created successfully",
                content = @Content(schema = @Schema(implementation = SolicitudResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ResponseApi<SolicitudResponseDto>>> createSolicitud(
            @Valid @RequestBody SolicitudRequestDto requestDto) {
        log.info("Creating new solicitud for marca: {}", requestDto.getMarcaId());
        
        return createSolicitudUseCase.execute(dtoMapper.toCreateCommand(requestDto))
                .map(dtoMapper::toResponseDto)
                .map(responseDto -> {
                    ResponseApi<SolicitudResponseDto> apiResponse = ResponseApi.<SolicitudResponseDto>builder()
                        .success(true)
                        .message("Solicitud creada exitosamente")
                        .data(responseDto)
                        .build();

                    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
                })
                .doOnSuccess(r -> log.info("Solicitud created successfully"))
                .doOnError(e -> log.error("Error creating solicitud", e));
    }

    @GetMapping
    @Operation(summary = "Listar Solicitudes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitudes retrieved successfully")
    })
    public Mono<ResponseEntity<ResponseApi<List<SolicitudDetalleResponseDto>>>> getSolicitudes(
        @RequestParam(name = "tipoSolicitud", required = false) String tipoSolicitud,
        @RequestParam(name = "marca", required = false) String marca,
        @RequestParam(name = "fechaEnvioDesde") LocalDate fechaEnvioDesde,
        @RequestParam(name = "fechaEnvioHasta") LocalDate fechaEnvioHasta
    )
    {
        log.info("tipoSolicitud: {}", tipoSolicitud);
        log.info("marca: {}", marca);
        log.info("fechaEnvioDesde: {}", fechaEnvioDesde);
        log.info("fechaEnvioHasta: {}", fechaEnvioHasta);

        SolicitudFiltro filtro = new SolicitudFiltro(tipoSolicitud, marca, fechaEnvioDesde, fechaEnvioHasta);
        return getSolicitudUseCase.execute(filtro)
                .map(dtoDetMapper::toResponseDto)
                .collectList()
                .map(lista -> {
                    ResponseApi<List<SolicitudDetalleResponseDto>> apiResponse = ResponseApi.<List<SolicitudDetalleResponseDto>>builder()
                        .success(true)
                        .message("Listado de marcas")
                        .data(lista)
                        .build();

                    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);                    
                });
    }
}
