package com.wora.ticket.domain.repositories;

import com.wora.common.contracts.BaseRepository;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;

import java.util.UUID;

public interface TicketRepository extends BaseRepository<Ticket, UUID> {
    void changeStatus(UUID id, TicketStatus status);
}
