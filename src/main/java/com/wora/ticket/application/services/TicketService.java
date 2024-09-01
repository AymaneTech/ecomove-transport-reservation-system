package com.wora.ticket.application.services;

import com.wora.ticket.application.dtos.requests.CreateTicketDto;
import com.wora.ticket.application.dtos.requests.UpdateTicketDto;
import com.wora.ticket.application.dtos.responses.TicketResponse;
import com.wora.ticket.domain.enums.TicketStatus;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    List<TicketResponse> findAll();

    TicketResponse findById(UUID id);

    void create(CreateTicketDto dto);

    void update(UUID id, UpdateTicketDto dto);

    void delete(UUID id);

    void changeStatus(UUID id, TicketStatus status);
}
