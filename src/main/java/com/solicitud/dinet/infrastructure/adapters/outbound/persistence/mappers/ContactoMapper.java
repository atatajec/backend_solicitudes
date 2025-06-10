package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.solicitud.dinet.domain.models.Contacto;
import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.ContactoEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactoMapper {

    Contacto toDomain(ContactoEntity entity);
    ContactoEntity toEntity(Contacto domain);
}
