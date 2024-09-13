package com.wora.ticket.application.services;

import com.wora.ticket.application.dtos.requests.SearchForTicketDto;
import com.wora.ticket.domain.entities.Ticket;

import java.util.List;

public interface SearchForTicketService {

    List<Ticket> search(SearchForTicketDto dto);
}
