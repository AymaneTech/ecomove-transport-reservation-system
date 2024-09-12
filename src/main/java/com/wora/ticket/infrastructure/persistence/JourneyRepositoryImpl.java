package com.wora.ticket.infrastructure.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.contract.domain.exceptions.ContractNotFoundException;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.infrastructure.mappers.JourneyResultSetMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.wora.common.utils.QueryExecutor.*;

public class JourneyRepositoryImpl extends BaseRepositoryImpl<Journey, UUID> implements JourneyRepository {

    public JourneyRepositoryImpl(JourneyResultSetMapper mapper) {
        super("journeys", mapper);
    }

    @Override
    public List<Journey> findAll() {
        final List<Journey> journeys = new ArrayList<>();
        final String query = """
                SELECT j.*, start_station.*, end_station.*
                    FROM journeys j
                    JOIN stations start_station ON j.start_id = start_station.id
                    JOIN stations end_station ON j.end_id = end_station.id;
                """;
        executeQueryStatement(query, rs -> {
            while (rs.next()) {
                journeys.add(mapper.map(rs));
            }
        });
        return journeys;
    }

    @Override
    public Optional<Journey> findById(UUID id) {
        final AtomicReference<Optional<Journey>> journey = new AtomicReference<>(Optional.empty());
        final String query = """
                SELECT j.*, start_station.*, end_station.*
                    FROM journeys j
                    JOIN stations start_station ON j.start_id = start_station.id
                    JOIN stations end_station ON j.end_id = end_station.id;
                    WHERE id = ?
                """;
        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, id.toString());
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                journey.set(Optional.of(mapper.map(rs)));
            }
        });
        return journey.get();
    }

    @Override

    public void create(Journey journey) {
        final String query = """
                INSERT INTO journeys
                (start_id, end_id, distance, id)
                VALUES (?, ?, ?, ?)
                """;
        executeUpdatePreparedStatement(query, stmt -> mapper.map(journey, stmt));
    }

    @Override
    public void update(UUID uuid, Journey journey) throws ContractNotFoundException {
        final String query = """
                UPDATE journeys
                SET start_id = ?, end_id = ?, distance = ?, updated_at = now()
                WHERE id = ?
                """;
        executeUpdatePreparedStatement(query, stmt -> mapper.map(journey, stmt));
    }

    @Override
    public Optional<Journey> findByStartAndEndStation(String startStationName, String endStationName) {
        final AtomicReference<Optional<Journey>> journey = new AtomicReference<>(Optional.empty());
        final String query = """
                SELECT j.*, start_station.*, end_station.*
                FROM journeys j
                JOIN stations start_station ON j.start_id = start_station.id
                JOIN stations end_station ON j.end_id = end_station.id
                WHERE start_station.name = ? AND end_station.name = ?
                AND j.deleted_at IS NULL
                AND start_station.deleted_at IS NULL
                AND end_station.deleted_at IS NULL
                """;

        executeQueryPreparedStatement(query, stmt -> {
            stmt.setObject(1, startStationName);
            stmt.setObject(2, endStationName);
            final ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                journey.set(Optional.of(mapper.map(rs)));
            }
        });
        return journey.get();
    }
}
