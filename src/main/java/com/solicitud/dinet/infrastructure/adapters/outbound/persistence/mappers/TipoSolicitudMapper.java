package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.TipoSolicitud;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.TipoSolicitudEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TipoSolicitudMapper {
    TipoSolicitud toDomain(TipoSolicitudEntity entity);
    TipoSolicitudEntity toEntity(TipoSolicitud domain);
}
