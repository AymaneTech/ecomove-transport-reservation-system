package com.wora.ticket.domain.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.util.Date;

public class Ticket extends AbstractEntity<TicketId> {
    private TicketId id;
    private ContractId contractId;
    private Price sellingPrice;
    private Price purchasePrice;
    private Date sellingDate;
    private TransportType transportType;
    private TicketStatus status;

    public Ticket(TicketId id, ContractId contractId, Price sellingPrice, Price purchasePrice, Date sellingDate, TransportType transportType, TicketStatus status) {
        this.id = id;
        this.contractId = contractId;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.sellingDate = sellingDate;
        this.transportType = transportType;
        this.status = status;
    }

    public Ticket(TicketId id, ContractId contractId, Price sellingPrice, Price purchasePrice, Date sellingDate, TransportType transportType, TicketStatus status,
                  Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.contractId = contractId;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.sellingDate = sellingDate;
        this.transportType = transportType;
        this.status = status;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public TicketId getId() {
        return id;
    }

    public Ticket setId(TicketId id) {
        this.id = id;
        return this;
    }

    public ContractId getContractId() {
        return contractId;
    }

    public Ticket setContractId(ContractId contractId) {
        this.contractId = contractId;
        return this;
    }

    public Price getSellingPrice() {
        return sellingPrice;
    }

    public Ticket setSellingPrice(Price sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public Price getPurchasePrice() {
        return purchasePrice;
    }

    public Ticket setPurchasePrice(Price purchasePrice) {
        this.purchasePrice = purchasePrice;
        return this;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public Ticket setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
        return this;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Ticket setTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public Ticket setStatus(TicketStatus status) {
        this.status = status;
        return this;
    }
}
