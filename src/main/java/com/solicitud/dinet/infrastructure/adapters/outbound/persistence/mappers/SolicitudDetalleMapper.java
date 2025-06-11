package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.SolicitudDetalle;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.SolicitudDetalleEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SolicitudDetalleMapper {

    SolicitudDetalle toDomain(SolicitudDetalleEntity entity);
    SolicitudDetalleEntity toEntity(SolicitudDetalle domain);

}
