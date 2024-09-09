package com.wora.ticket.domain.exceptions;

import java.util.UUID;

public class JourneyNotFoundException extends RuntimeException {
    private final UUID id;
    private final String startStationName;
    private final String endStationName;

    public JourneyNotFoundException(UUID id) {
        super("journey with id " + id + "not found");
        this.id = id;
        this.startStationName = null;
        this.endStationName = null;
    }

    public JourneyNotFoundException(String startStationName, String endStationName) {
        super("journey with start and end staitons not found " + startStationName + " -> " + endStationName);
        this.startStationName = startStationName;
        this.endStationName = endStationName;
        this.id = null;
    }
}
