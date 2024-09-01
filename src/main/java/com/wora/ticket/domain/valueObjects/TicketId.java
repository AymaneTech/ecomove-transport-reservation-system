package com.wora.ticket.domain.valueObjects;

import java.util.UUID;

public record TicketId(UUID value) {

    public TicketId() {
        this(UUID.randomUUID());
    }
}
