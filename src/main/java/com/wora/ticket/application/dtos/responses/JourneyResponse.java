package com.wora.ticket.application.dtos.responses;

public record JourneyResponse(StationResponse start, StationResponse end, Double distance) {
}
