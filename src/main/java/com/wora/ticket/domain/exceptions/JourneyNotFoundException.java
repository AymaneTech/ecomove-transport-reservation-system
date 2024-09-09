package com.wora.ticket.domain.exceptions;

import java.util.UUID;

public class JourneyNotFoundException extends RuntimeException {
    private final UUID id;

    public JourneyNotFoundException(UUID id) {
        super("journey with id " + id + "not found");
        this.id = id;
    }
}
