package com.wora.ticket.infrastructure.mappers;

import com.wora.common.infrastructure.mappers.BaseEntityResultSetMapper;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.UUID;

public class TicketResultSetMapper implements BaseEntityResultSetMapper<Ticket> {
    @Override
    public Ticket map(ResultSet resultSet) {
        try {
            return new Ticket(
                    new TicketId((UUID) resultSet.getObject("id")),
                    new ContractId((UUID) resultSet.getObject("contract_id")),
                    new Price(
                            resultSet.getFloat("selling_price_amount"),
                            (Currency) resultSet.getObject("selling_price_currency")
                    ),
                    new Price(
                            resultSet.getFloat("purchase_price_amount"),
                            (Currency) resultSet.getObject("purchase_price_currency")
                    ),
                    resultSet.getDate("selling_date"),
                    (TransportType) resultSet.getObject("transport_type"),
                    (TicketStatus) resultSet.getObject("ticket_status"),
                    resultSet.getDate("created_at"),
                    resultSet.getDate("updated_at"),
                    resultSet.getDate("deleted_at")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void map(Ticket ticket, PreparedStatement stmt) {
        int count = 1;

        try {
            stmt.setObject(count++, ticket.getContractId().value());
            stmt.setObject(count++, ticket.getSellingPrice().amount());
            stmt.setObject(count++, ticket.getSellingPrice().currency());
            stmt.setObject(count++, ticket.getPurchasePrice().amount());
            stmt.setObject(count++, ticket.getPurchasePrice().currency());
            stmt.setDate(count++, new Date(ticket.getSellingDate().getTime()));
            stmt.setObject(count++, ticket.getTransportType().toString());
            stmt.setObject(count++, ticket.getStatus().toString());
            stmt.setObject(count++, ticket.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
