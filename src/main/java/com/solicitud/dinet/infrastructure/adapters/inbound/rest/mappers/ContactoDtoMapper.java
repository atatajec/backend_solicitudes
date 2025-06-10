package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.application.commands.contacto.CreateContactoCommand;
import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto.ContactoRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.contacto.ContactoResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactoDtoMapper {
    ContactoResponseDto toResponseDto(Contacto contacto);

    @Mapping(target = "usuario", source = "usuario", defaultValue = "SYSTEM")
    CreateContactoCommand toCreateCommand(ContactoRequestDto requestDto);
}
