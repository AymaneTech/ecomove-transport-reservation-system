package com.wora.ticket.application.mappers;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.ticket.application.dtos.requests.CreateTicketDto;
import com.wora.ticket.application.dtos.requests.UpdateTicketDto;
import com.wora.ticket.application.dtos.responses.TicketResponse;
import com.wora.ticket.domain.entities.Ticket;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.util.UUID;

public class TicketMapper {
    public Ticket map(CreateTicketDto dto) {
        return new Ticket(
                new TicketId(),
                dto.contractId(),
                dto.sellingPrice(),
                dto.purchasePrice(),
                dto.traject(),
                dto.transportType(),
                dto.status()
        );
    }

    public Ticket map(UpdateTicketDto dto, UUID id) {
        return new Ticket(
                new TicketId(id),
                dto.contractId(),
                dto.sellingPrice(),
                dto.purchasePrice(),
                dto.traject(),
                dto.transportType(),
                dto.status()
        );
    }

    public TicketResponse map(Ticket ticket, ContractResponse contractResponse) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getSellingPrice(),
                ticket.getPurchasePrice(),
                ticket.getSellingDate(),
                ticket.getTransportType(),
                ticket.getStatus(),
                contractResponse,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}
