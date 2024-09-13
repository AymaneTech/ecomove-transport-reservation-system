package com.wora.ticket.application.services.impl;

import com.wora.ticket.application.dtos.requests.SearchForTicketDto;
import com.wora.ticket.application.services.SearchForTicketService;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.domain.repositories.TicketRepository;

import java.util.List;

public class SearchForTicketServiceImpl implements SearchForTicketService {
    private final StationRepository stationRepository;
    private final JourneyRepository journeyRepository;
    private final TicketRepository ticketRepository;

    public SearchForTicketServiceImpl(StationRepository stationRepository, JourneyRepository journeyRepository, TicketRepository ticketRepository) {
        this.stationRepository = stationRepository;
        this.journeyRepository = journeyRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> search(SearchForTicketDto dto) {
        final List<Station> stations = this.stationRepository.findAll();
        final List<Journey> journeys = this.journeyRepository.findAll();



        return List.of();
    }
}
