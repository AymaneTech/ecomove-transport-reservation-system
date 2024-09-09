package com.wora.ticket.application.dtos.requests;

import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;

public record UpdateTicketDto(
        ContractId contractId,
        Price sellingPrice,
        Price purchasePrice,
        Journey journey,
        TransportType transportType,
        TicketStatus status
) {
}
