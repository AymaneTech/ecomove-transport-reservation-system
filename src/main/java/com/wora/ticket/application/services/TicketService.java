package com.wora.ticket.application.services;

import com.wora.ticket.application.dtos.requests.CreateTicketDto;
import com.wora.ticket.application.dtos.requests.UpdateTicketDto;
import com.wora.ticket.application.dtos.responses.TicketResponse;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.util.List;

public interface TicketService {

    List<TicketResponse> findAll();

    TicketResponse findById(TicketId id);

    void create(CreateTicketDto dto);

    void update(TicketId id, UpdateTicketDto dto);

    void delete(TicketId id);

    void changeStatus(TicketId id, TicketStatus status);

    Boolean existsById(TicketId id);
}
