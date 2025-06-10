package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.ContactoSolicitudEntity;

public interface ContactoSolicitudR2dbcRepository extends R2dbcRepository<ContactoSolicitudEntity, UUID> {

}
