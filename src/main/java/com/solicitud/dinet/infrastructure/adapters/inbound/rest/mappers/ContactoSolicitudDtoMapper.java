package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.application.commands.contactosolicitud.CreateContactoSolicitudCommand;
import com.solicitud.dinet.domain.models.ContactoSolicitud;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud.ContactoSolicitudRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contactosolicitud.ContactoSolicitudResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactoSolicitudDtoMapper {
    ContactoSolicitudResponseDto toResponseDto(ContactoSolicitud contactoSolicitud);

    @Mapping(target = "usuario", source = "usuario", defaultValue = "SYSTEM")
    CreateContactoSolicitudCommand toCreateCommand(ContactoSolicitudRequestDto requestDto);
}
