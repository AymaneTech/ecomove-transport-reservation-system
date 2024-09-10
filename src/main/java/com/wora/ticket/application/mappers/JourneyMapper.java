package com.wora.ticket.application.mappers;


import com.wora.ticket.application.dtos.requests.CreateJourneyDto;
import com.wora.ticket.application.dtos.requests.UpdateJourneyDto;
import com.wora.ticket.application.dtos.responses.JourneyResponse;
import com.wora.ticket.application.dtos.responses.StationResponse;
import com.wora.ticket.application.mappers.StationMapper;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.valueObjects.JourneyId;

import java.util.UUID;

public class JourneyMapper {

    private final StationMapper stationMapper = new StationMapper();

    public Journey map(CreateJourneyDto dto, Station start, Station end) {
        return new Journey(
                new JourneyId(),
                start,
                end,
                dto.distance()
        );
    }

    public Journey map(JourneyId id, UpdateJourneyDto dto, Station start, Station end) {
        return new Journey(
                id,
                start,
                end,
                dto.distance()
        );
    }

    public JourneyResponse map(Journey journey) {
        StationResponse startStation = stationMapper.map(journey.getStart());
        StationResponse endStation = stationMapper.map(journey.getEnd());

        return new JourneyResponse(
                startStation,
                endStation,
                journey.getDistance()
        );
    }
}
