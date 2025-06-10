package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.application.commands.solicitud.CreateSolicitudCommand;
import com.solicitud.dinet.domain.models.Solicitud;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.solicitud.SolicitudResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolicitudDtoMapper {

    SolicitudResponseDto toResponseDto(Solicitud solicitud);
    
    @Mapping(target = "usuario", source = "usuario", defaultValue = "SYSTEM")
    CreateSolicitudCommand toCreateCommand(SolicitudRequestDto requestDto);
    
}
