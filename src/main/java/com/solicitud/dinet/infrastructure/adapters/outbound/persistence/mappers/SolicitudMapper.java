package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import com.solicitud.dinet.domain.models.Solicitud;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudEntity;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolicitudMapper {

    Solicitud toDomain(SolicitudEntity entity);    
    SolicitudEntity toEntity(Solicitud domain);

}
