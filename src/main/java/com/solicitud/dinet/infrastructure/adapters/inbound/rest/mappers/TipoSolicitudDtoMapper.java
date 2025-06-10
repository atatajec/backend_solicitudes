package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.application.commands.tiposolicitud.CreateTipoSolicitudCommand;
import com.solicitud.dinet.domain.models.TipoSolicitud;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.tiposolicitud.TipoSolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.tiposolicitud.TipoSolicitudResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoSolicitudDtoMapper {
    TipoSolicitudResponseDto toResponseDto(TipoSolicitud solicitud);
    
    @Mapping(target = "usuario", source = "usuario", defaultValue = "SYSTEM")
    CreateTipoSolicitudCommand toCreateCommand(TipoSolicitudRequestDto requestDto);
}
