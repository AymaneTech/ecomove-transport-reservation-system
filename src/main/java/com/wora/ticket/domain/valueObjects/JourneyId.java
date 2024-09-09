package com.wora.ticket.domain.valueObjects;

import java.util.UUID;

public record JourneyId(UUID value) {
    public JourneyId() {
        this(UUID.randomUUID());
    }
}
