package com.wora.ticket.domain.exceptions;

import java.util.UUID;

public class StationNotFoundException extends RuntimeException {
    private final UUID id;
    private final String cityName;

    public StationNotFoundException(UUID id) {
        super("station with id " + id + "not found");
        this.id = id;
        this.cityName = null;
    }

    public StationNotFoundException(String cityName) {
        super("station with city name " + cityName + " not found");
        this.id = null;
        this.cityName = cityName;
    }
}
