package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.valueObjects.StationId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StationResultSetMapper implements BaseEntityResultSetMapper<Station> {
    @Override
    public Station map(ResultSet resultSet) {
        try {
            return new Station(
                    new StationId((UUID) resultSet.getObject("id")),
                    resultSet.getString("name"),
                    resultSet.getString("city")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(Station station, PreparedStatement stmt) {
        int count = 1;
        try {
            stmt.setString(count++, station.getName());
            stmt.setString(count++, station.getCity());
            stmt.setObject(count++, station.getId().value());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}