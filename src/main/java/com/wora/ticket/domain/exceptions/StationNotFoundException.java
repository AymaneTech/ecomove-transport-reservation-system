package com.wora.ticket.domain.exceptions;

import com.wora.ticket.domain.valueObjects.StationId;

import java.util.UUID;

public class StationNotFoundException extends RuntimeException {
    private final UUID id;

    public StationNotFoundException(UUID id) {
        super("station with id " + id + "not found");
        this.id = id;
    }
}
