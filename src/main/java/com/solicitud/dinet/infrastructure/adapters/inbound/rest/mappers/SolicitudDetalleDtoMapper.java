package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.SolicitudDetalle;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudDetalleResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolicitudDetalleDtoMapper {

    SolicitudDetalleResponseDto toResponseDto(SolicitudDetalle detalle);
}
