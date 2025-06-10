package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.Marca;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.MarcaEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MarcaMapper {

    Marca toDomain(MarcaEntity entity);
    MarcaEntity toEntity(Marca domain);
    
}
