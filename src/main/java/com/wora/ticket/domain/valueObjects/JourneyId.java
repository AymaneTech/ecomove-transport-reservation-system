package com.wora.ticket.domain.valueObjects;

import java.util.UUID;

public record TrajectId(UUID value) {
    public TrajectId() {
        this(UUID.randomUUID());
    }
}
