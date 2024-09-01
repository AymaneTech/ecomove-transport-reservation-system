package com.wora.ticket.application.services.impl;

import com.wora.contract.application.services.ContractService;
import com.wora.ticket.application.dtos.requests.CreateTicketDto;
import com.wora.ticket.application.dtos.requests.UpdateTicketDto;
import com.wora.ticket.application.dtos.responses.TicketResponse;
import com.wora.ticket.application.mappers.TicketMapper;
import com.wora.ticket.application.services.TicketService;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.repositories.TicketRepository;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.util.List;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository repository;
    private final ContractService contractService;
    private final TicketMapper mapper;

    public TicketServiceImpl(TicketRepository repository, ContractService contractService, TicketMapper mapper) {
        this.repository = repository;
        this.contractService = contractService;
        this.mapper = mapper;
    }

    @Override
    public List<TicketResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(ticket -> mapper.map(ticket, contractService.findById(ticket.getContractId())))
                .toList();
    }

    @Override
    public TicketResponse findById(TicketId id) {
        return repository.findById(id.value())
                .map(ticket -> mapper.map(ticket, contractService.findById(ticket.getContractId())))
                .orElseThrow();
    }

    @Override
    public void create(CreateTicketDto dto) {
        final Ticket ticket = mapper.map(dto);
        repository.create(ticket);
    }

    @Override
    public void update(TicketId id, UpdateTicketDto dto) {
        final Ticket ticket = mapper.map(dto, id.value());
        repository.update(id.value(), ticket);
    }

    @Override
    public void delete(TicketId id) {
        repository.delete(id.value());
    }

    @Override
    public void changeStatus(TicketId id, TicketStatus status) {
        repository.changeStatus(id.value(), status);
    }
}
