package com.wora.partner.domain.entities;

import java.sql.Date;
import java.util.UUID;

import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;

/**
 * Partner
 */
public class Partner {

    private UUID id;

    private String name;

    private String commercialName;

    private String commercialPhoneNumber;

    private String commercialEmail;

    private String geographicArea;

    private String specialCondition;

    private TransportType type;

    private PartnerStatus status;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    Partner() {
    }

    public Partner(UUID id, String name, String commercialName, String commercialPhoneNumber, String commercialEmail,
            String geographicArea, String specialCondition, TransportType type, PartnerStatus status, Date createdAt) {
        this.id = id;
        this.name = name;
        this.commercialName = commercialName;
        this.commercialPhoneNumber = commercialPhoneNumber;
        this.commercialEmail = commercialEmail;
        this.geographicArea = geographicArea;
        this.specialCondition = specialCondition;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return this.id;
    }

    public Partner setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Partner setName(String name) {
        this.name = name;
        return this;
    }

    public String getCommercialName() {
        return this.commercialName;
    }

    public Partner setCommercialName(String commercialName) {
        this.commercialName = commercialName;
        return this;
    }

    public String getCommercialPhoneNumber() {
        return this.commercialPhoneNumber;
    }

    public Partner setCommercialPhoneNumber(String commercialPhoneNumber) {
        this.commercialPhoneNumber = commercialPhoneNumber;
        return this;
    }

    public String getCommercialEmail() {
        return this.commercialEmail;
    }

    public Partner setCommercialEmail(String commercialEmail) {
        this.commercialEmail = commercialEmail;
        return this;
    }

    public String getGeographicArea() {
        return this.geographicArea;
    }

    public Partner setGeographicArea(String geographicArea) {
        this.geographicArea = geographicArea;
        return this;
    }

    public String getSpecialCondition() {
        return this.specialCondition;
    }

    public Partner setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
        return this;
    }

    public TransportType getType() {
        return this.type;
    }

    public Partner setType(TransportType type) {
        this.type = type;
        return this;
    }

    public PartnerStatus getStatus() {
        return this.status;
    }

    public Partner setStatus(PartnerStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Partner setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public Partner setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeletedAt() {
        return this.deletedAt;
    }

    public Partner setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
