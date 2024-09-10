package com.wora.ticket.domain.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.time.LocalDateTime;
import java.util.Date;

public class Ticket extends AbstractEntity<TicketId> {
    private TicketId id;
    private ContractId contractId;
    private Price sellingPrice;
    private Price purchasePrice;
    private Date sellingDate;
    private Journey journey;
    private LocalDateTime journeyStartDate;
    private LocalDateTime journeyEndDate;
    private TransportType transportType;
    private TicketStatus status;

    public Ticket(TicketId id, ContractId contractId, Price sellingPrice, Price purchasePrice, Journey journey, LocalDateTime journeyStartDate, LocalDateTime journeyEndDate, TransportType transportType, TicketStatus status) {
        this.id = id;
        this.contractId = contractId;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.journey = journey;
        this.journeyStartDate = journeyStartDate;
        this.journeyEndDate = journeyEndDate;
        this.transportType = transportType;
        this.status = status;
    }

    public Ticket(TicketId id, ContractId contractId, Price sellingPrice, Price purchasePrice, Journey journey, Date sellingDate, LocalDateTime journeyStartDate, LocalDateTime journeyEndDate, TransportType transportType, TicketStatus status,
                  Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.contractId = contractId;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.sellingDate = sellingDate;
        this.journey = journey;
        this.journeyStartDate = journeyStartDate;
        this.journeyEndDate = journeyEndDate;
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

    public LocalDateTime getJourneyEndDate() {
        return journeyEndDate;
    }

    public Ticket setJourneyEndDate(LocalDateTime journeyEndDate) {
        this.journeyEndDate = journeyEndDate;
        return this;
    }

    public LocalDateTime getJourneyStartDate() {
        return journeyStartDate;
    }

    public Ticket setJourneyStartDate(LocalDateTime journeyStartDate) {
        this.journeyStartDate = journeyStartDate;
        return this;
    }

    public Journey getJourney() {
        return journey;
    }

    public Ticket setJourney(Journey journey) {
        this.journey = journey;
        return this;
    }
}
