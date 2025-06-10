package com.solicitud.dinet.infrastructure.adapters.outbound.persistence.repositories;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.solicitud.dinet.infrastructure.adapters.outbound.persistence.entities.MarcaEntity;

public interface MarcaR2dbcRepository extends R2dbcRepository<MarcaEntity, UUID> {

}
