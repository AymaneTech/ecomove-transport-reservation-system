package com.wora.ticket.application.services;

import com.wora.ticket.application.dtos.requests.CreateStationDto;
import com.wora.ticket.application.dtos.requests.UpdateStationDto;
import com.wora.ticket.application.dtos.responses.StationResponse;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.List;

public interface StationService {
    List<StationResponse> findAll();

    StationResponse findById(StationId id);

    void create(CreateStationDto dto);

    void update(StationId id, UpdateStationDto dto);

    void delete(StationId id);

    Boolean existsById(StationId id);
}
