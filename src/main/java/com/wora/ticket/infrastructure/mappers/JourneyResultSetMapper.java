package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.valueObjects.JourneyId;
import com.wora.ticket.domain.valueObjects.StationId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.UUID;

public class JourneyResultSetMapper implements BaseEntityResultSetMapper<Journey> {
    private final StationResultSetMapper stationMapper;

    public JourneyResultSetMapper(StationResultSetMapper stationMapper) {
        this.stationMapper = stationMapper;
    }

    @Override

    public Journey map(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
//        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//            System.out.println(i + " -> " + rsmd.getColumnName(i));
//        }

        return new Journey(
                new JourneyId(UUID.fromString(resultSet.getString(1))),
                new Station(
                        new StationId(UUID.fromString(resultSet.getString(8))),
                        resultSet.getString(9),
                        resultSet.getString(10)
                ),
                new Station(
                        new StationId(UUID.fromString(resultSet.getString(14))),
                        resultSet.getString(15),
                        resultSet.getString(16)
                ),
                resultSet.getDouble(4)
        );
    }

    @Override
    public void map(Journey journey, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setObject(count++, journey.getStart().getId().value());
        stmt.setObject(count++, journey.getEnd().getId().value());
        stmt.setDouble(count++, journey.getDistance());
        stmt.setObject(count++, journey.getId().value());
    }
}