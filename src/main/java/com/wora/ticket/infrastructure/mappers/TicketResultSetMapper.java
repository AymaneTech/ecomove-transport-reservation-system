package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.UUID;

public class TicketResultSetMapper implements BaseEntityResultSetMapper<Ticket> {
    private final JourneyResultSetMapper journeyMapper;

    public TicketResultSetMapper(JourneyResultSetMapper journeyMapper) {
        this.journeyMapper = journeyMapper;
    }

    @Override
    public Ticket map(ResultSet rs) throws SQLException {
        return new Ticket(
                new TicketId((UUID) rs.getObject("id")),
                new ContractId((UUID) rs.getObject("contract_id")),
                new Price(
                        rs.getFloat("selling_price_amount"),
                        (Currency) rs.getObject("selling_price_currency")
                ),
                new Price(
                        rs.getFloat("purchase_price_amount"),
                        (Currency) rs.getObject("purchase_price_currency")
                ),
                journeyMapper.map(rs),
                rs.getDate("selling_date"),
                rs.getTimestamp("journey_start_date").toLocalDateTime(),
                rs.getTimestamp("journey_end_date").toLocalDateTime(),
                (TransportType) rs.getObject("transport_type"),
                (TicketStatus) rs.getObject("ticket_status"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime(),
                rs.getTimestamp("deleted_at").toLocalDateTime()
        );
    }

    @Override
    public void map(Ticket ticket, PreparedStatement stmt) throws SQLException {
        int count = 1;
        stmt.setObject(count++, ticket.getContractId().value());
        stmt.setObject(count++, ticket.getSellingPrice().amount());
        stmt.setObject(count++, ticket.getSellingPrice().currency().getCurrencyCode());
        stmt.setObject(count++, ticket.getPurchasePrice().amount());
        stmt.setObject(count++, ticket.getPurchasePrice().currency().getCurrencyCode());
        stmt.setObject(count++, ticket.getTransportType().toString());
        stmt.setObject(count++, ticket.getStatus().toString());
        stmt.setObject(count++, ticket.getJourneyStartDate());
        stmt.setObject(count++, ticket.getJourneyEndDate());
        stmt.setObject(count++, ticket.getJourney().getId().value());
        stmt.setObject(count++, ticket.getId().value());
    }
}
