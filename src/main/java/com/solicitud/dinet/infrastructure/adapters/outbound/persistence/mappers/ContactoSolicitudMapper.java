package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.ContactoSolicitud;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.ContactoSolicitudEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactoSolicitudMapper {
    ContactoSolicitud toDomain(ContactoSolicitudEntity entity);
    ContactoSolicitudEntity toEntity(ContactoSolicitud domain);
}
