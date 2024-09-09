package com.wora.ticket.application.dtos.requests;

import com.wora.ticket.domain.valueObjects.StationId;

public record CreateJourneyDto(StationId startId, StationId endId, Double distance) {
}
