package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.ticket.domain.entities.Traject;
import com.wora.ticket.domain.valueObjects.TrajectId;
import com.wora.ticket.infrastructure.mappers.StationResultSetMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TrajectResultSetMapper implements BaseEntityResultSetMapper<Traject> {
    private final StationResultSetMapper stationMapper;

    public TrajectResultSetMapper(StationResultSetMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    @Override
    public Traject map(ResultSet resultSet) {
        try {
            return new Traject(
                    new TrajectId((UUID) resultSet.getObject("id")),
                    stationMapper.map(resultSet),
                    stationMapper.map(resultSet),
                    resultSet.getDouble("distance")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(Traject traject, PreparedStatement stmt) {
        int count = 1;
        try {
            stmt.setObject(count++, traject.getId().value());
            stmt.setObject(count++, traject.getStart().getId().value());
            stmt.setObject(count++, traject.getEnd().getId().value());
            stmt.setDouble(count++, traject.getDistance());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}