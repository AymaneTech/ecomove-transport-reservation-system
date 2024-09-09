package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.valueObjects.JourneyId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JourneyResultSetMapper implements BaseEntityResultSetMapper<Journey> {
    private final StationResultSetMapper stationMapper;

    public JourneyResultSetMapper(StationResultSetMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    @Override
    public Journey map(ResultSet resultSet) {
        try {
            return new Journey(
                    new JourneyId((UUID) resultSet.getObject("id")),
                    stationMapper.map(resultSet),
                    stationMapper.map(resultSet),
                    resultSet.getDouble("distance")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(Journey journey, PreparedStatement stmt) {
        int count = 1;
        try {
            stmt.setObject(count++, journey.getStart().getId().value());
            stmt.setObject(count++, journey.getEnd().getId().value());
            stmt.setDouble(count++, journey.getDistance());
            stmt.setObject(count++, journey.getId().value());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}