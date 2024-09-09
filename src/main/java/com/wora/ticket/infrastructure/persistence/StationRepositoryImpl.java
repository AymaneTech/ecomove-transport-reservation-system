package com.wora.ticket.infrastructure.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.contract.domain.exceptions.ContractNotFoundException;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.infrastructure.mappers.StationResultSetMapper;

import java.util.Optional;
import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class StationRepositoryImpl extends BaseRepositoryImpl<Station, UUID> implements StationRepository {
    private final StationResultSetMapper mapper;

    public StationRepositoryImpl(StationResultSetMapper mapper) {
        super("stations", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Station station) {
        final String query = """
                INSERT INTO stations
                (name, city, id) VALUES (?, ?, ?)
                """;
        executeUpdatePreparedStatement(query, stmt -> mapper.map(station, stmt));
    }

    @Override
    public void update(UUID uuid, Station station) throws ContractNotFoundException {
        final String query = """
                UPDATE stations
                SET name=?, city?,
                updated_at = NOW()
                WHERE id=?
                """;
        executeUpdatePreparedStatement(query, stmt -> mapper.map(station, stmt));
    }

    @Override
    public Optional<Station> findByCityName(String cityName) {
        return this.findByColumn("city", cityName);
    }
}
