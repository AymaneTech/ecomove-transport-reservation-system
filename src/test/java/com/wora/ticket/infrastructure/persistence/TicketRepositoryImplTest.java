package com.wora.ticket.infrastructure.persistence;

import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.infrastructure.mappers.TicketResultSetMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class TicketRepositoryImplTest {
    private TicketRepositoryImpl ticketRepositoryImpl;
    private TicketResultSetMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = mock(TicketResultSetMapper.class);
        this.ticketRepositoryImpl = new TicketRepositoryImpl(mapper);
    }

    @Test
    public void findAll_ShouldReturnListOfPartnerResponses() {
        // unit under test
        final List<Ticket> tickeets = this.ticketRepositoryImpl.findAll();

//        when(mapper.map()).then()

    }

}