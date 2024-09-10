package com.wora.ticket.infrastructure.persistence;

import com.wora.common.infrastructure.persistence.BaseRepositoryImpl;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.repositories.TicketRepository;
import com.wora.ticket.infrastructure.mappers.TicketResultSetMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wora.common.utils.QueryExecutor.executeQueryStatement;
import static com.wora.common.utils.QueryExecutor.executeUpdatePreparedStatement;

public class TicketRepositoryImpl extends BaseRepositoryImpl<Ticket, UUID> implements TicketRepository {
    private final TicketResultSetMapper mapper;
    private final String tableName = "tickets";

    public TicketRepositoryImpl(TicketResultSetMapper mapper) {
        super("tickets", mapper);
        this.mapper = mapper;
    }

    @Override
    public List<Ticket> findAll() {
        final List<Ticket> tickets = new ArrayList<>();
        final String query = "SELECT t.* FROM tickets t JOIN journeys j ON t.journey_id = j.id WHERE t.deleted_at IS NULL";

        executeQueryStatement(query, rs -> {
            while (rs.next()) {
                tickets.add(mapper.map(rs));
            }
        });
        return tickets;
    }

    @Override
    public void create(Ticket ticket) {
        final String query = String.format("""
                INSERT INTO %s
                (contract_id, selling_price_amount, selling_price_currency, purchase_price_amount, purchase_price_currency, transport_type, ticket_status, journey_start_date::TIMESTAMP, journey_end_date::TIMESTAMP, journey_id, id)
                VALUES (?, ?, ?, ?, ?, CAST(? AS transport_type), CAST(? AS ticket_status), ?)
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
                journey_start_date = ?::TIMESTAMP,
                journey_end_date = ?::TIMESTAMP,
                journey_id = ?
                WHERE id = ?
                AND deleted_at IS NOT NULL
                """, tableName);
        executeUpdatePreparedStatement(query, stmt -> mapper.map(ticket, stmt));
    }

    @Override
    public void changeStatus(UUID id, TicketStatus status) {
        final String query = String.format("""
                UPDATE %s
                SET ticket_status = ?
                updated_at CURRENT_TIMESTAMP
                WHERE id = ?
                """, tableName);

        executeUpdatePreparedStatement(query, stmt -> {
            stmt.setObject(1, status);
            stmt.setString(2, id.toString());
        });
    }
}
