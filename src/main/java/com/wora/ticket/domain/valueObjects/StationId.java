package com.wora.ticket.domain.valueObjects;

import java.util.UUID;

public record StationId(UUID value) {
    public StationId() {
        this(UUID.randomUUID());
    }
}
