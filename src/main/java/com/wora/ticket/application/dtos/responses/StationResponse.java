package com.wora.ticket.application.dtos.responses;

import com.wora.ticket.domain.valueObjects.StationId;

public record StationResponse(StationId id, String name, String city) {
}
