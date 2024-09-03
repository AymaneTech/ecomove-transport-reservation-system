package com.wora.ticket.application.dtos.responses;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.util.Date;

public record TicketResponse(
        TicketId id,
        Price sellingPrice,
        Price purchasePrice,
        Date sellingDate,
        TransportType transportType,
        TicketStatus status,
        ContractResponse contract,
        Date createdAt,
        Date updatedAt
) {
}
