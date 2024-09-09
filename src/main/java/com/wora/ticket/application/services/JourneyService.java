package com.wora.ticket.application.services;

import com.wora.ticket.application.dtos.requests.CreateJourneyDto;
import com.wora.ticket.application.dtos.requests.UpdateJourneyDto;
import com.wora.ticket.application.dtos.responses.JourneyResponse;
import com.wora.ticket.domain.valueObjects.JourneyId;

import java.util.List;

public interface JourneyService {
    List<JourneyResponse> findAll();

    JourneyResponse findById(JourneyId id);

    void create(CreateJourneyDto dto);

    void update(JourneyId id, UpdateJourneyDto dto);

    void delete(JourneyId id);

    Boolean existsById(JourneyId id);
}
