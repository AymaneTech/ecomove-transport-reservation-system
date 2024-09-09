package com.wora.ticket.infrastructure.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.contract.domain.exceptions.ContractNotFoundException;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.infrastructure.mappers.StationResultSetMapper;

import java.util.UUID;

public class StationRepositoryImpl extends BaseRepositoryImpl<Station, UUID> implements StationRepository {
    private final StationResultSetMapper mapper;

    public StationRepositoryImpl(StationResultSetMapper mapper) {
        super("stations", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Station station) {
    }

    @Override
    public void update(UUID uuid, Station station) throws ContractNotFoundException {

    }
}
