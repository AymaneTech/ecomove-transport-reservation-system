package com.wora.ticket.domain.exceptions;

import com.wora.ticket.infrastructure.presentation.TicketUi;

import java.util.UUID;

public class TicketNotFoundException extends RuntimeException {
    private final UUID id;

    public TicketNotFoundException(UUID id) {
        super("ticket with id " + id + "not found");
        this.id = id;
    }
}
