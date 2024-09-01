package com.wora.ticket.infrastructure.persistence;

import com.wora.common.repositories.BaseRepositoryImpl;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.repositories.TicketRepository;
import com.wora.ticket.infrastructure.mappers.TicketResultSetMapper;

import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket, UUID> implements TicketRepository {
    private final TicketResultSetMapper mapper;
    private final String tableName = "tickets";

    public TicketRepositoryImpl(TicketResultSetMapper mapper) {
        super("tickets", mapper);
        this.mapper = mapper;
    }

    @Override
    public void create(Ticket ticket) {
        final String query = String.format("""
                INSERT INTO %s
                (contract_id, selling_price_amount, selling_price_currency, purchase_price_amount, purchase_purchase_currency, selling_date, transport_type, ticket_status, id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS contract_status))
                """, tableName);
        executeUpdatePreparedStatement(query, stmt -> mapper.map(ticket, stmt));
    }

    @Override
    public void update(UUID id, Ticket ticket) {
        final String query = String.format("""
                UPDATE %s
                SET contract_id = ?,
                selling_price_amount = ?,
                selling_price_currency = ?,
                purchase_price_amount = ?,
                purchase_price_currency = ?,
                selling_date = ?,
                transport_type = (CAST ? AS transport_type),
                ticket_status = (CAST ? AS ticket_status)
                WHERE id = ?,
                AND deleted_at IS NOT NULL
                """);
        executeUpdatePreparedStatement(query, stmt -> mapper.map(ticket, stmt));
    }

    @Override
    public void changeStatus(UUID id, TicketStatus status) {
        final String query = String.format("""
                UPDATE %s
                SET ticket_status = ?
                WHERE id = ?
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> {
            stmt.setObject(1, status);
            stmt.setString(2, id.toString());
        });
    }
}
