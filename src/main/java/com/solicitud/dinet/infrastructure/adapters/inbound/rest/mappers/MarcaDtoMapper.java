package com.solicitud.dinet.infrastructure.adapters.inbound.rest.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.application.commands.marca.CreateMarcaCommand;
import com.solicitud.dinet.domain.models.Marca;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca.MarcaRequestDto;
import com.solicitud.dinet.infrastructure.adapters.inbound.rest.dto.marca.MarcaResponseDto;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcaDtoMapper {
    MarcaResponseDto toResponseDto(Marca marca);

    @Mapping(target = "usuario", source = "usuario", defaultValue = "SYSTEM")
    CreateMarcaCommand toCreateCommand(MarcaRequestDto requestDto);
}
