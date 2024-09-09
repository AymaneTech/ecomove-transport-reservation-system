package com.wora.ticket.application.mappers;

import com.wora.ticket.application.dtos.requests.CreateStationDto;
import com.wora.ticket.application.dtos.requests.UpdateStationDto;
import com.wora.ticket.application.dtos.responses.StationResponse;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.UUID;

public class StationMapper {
    public Station map(CreateStationDto dto) {
        return new Station(
                new StationId(),
                dto.name(),
                dto.city()
        );
    }

    public Station map(UpdateStationDto dto, UUID id) {
        return new Station(
                new StationId(id),
                dto.name(),
                dto.city()
        );
    }

    public StationResponse map(Station station) {
        return new StationResponse(
                station.getId(),
                station.getName(),
                station.getCity()
        );
    }
}
