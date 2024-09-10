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
    public Station map(ResultSet resultSet) throws SQLException {
        return new Station(
                new StationId((UUID) resultSet.getObject("id")),
                resultSet.getString("name"),
                resultSet.getString("city")
        );
    }

    @Override
    public void map(Station station, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setString(count++, station.getName());
        stmt.setString(count++, station.getCity());
        stmt.setObject(count++, station.getId().value());
    }
}