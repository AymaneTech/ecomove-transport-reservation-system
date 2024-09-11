package com.wora.partner.domain.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;
import com.wora.partner.domain.valueObjects.CommercialInfo;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Partner
 */
public class Partner extends AbstractEntity<PartnerId> {
    private PartnerId id;
    private String name;
    private CommercialInfo commercialInfo;
    private String geographicArea;
    private String specialCondition;
    private TransportType transportType;
    private PartnerStatus status;

    public Partner(PartnerId id, String name, CommercialInfo commercialInfo,
                   String geographicArea, String specialCondition, TransportType type, PartnerStatus status) {
        this.id = id;
        this.name = name;
        this.commercialInfo = commercialInfo;
        this.geographicArea = geographicArea;
        this.specialCondition = specialCondition;
        this.transportType = type;
        this.status = status;
    }

    public Partner(PartnerId id, String name, CommercialInfo commercialInfo,
                   String geographicArea, String specialCondition, TransportType type, PartnerStatus status,
                   LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.commercialInfo = commercialInfo;
        this.geographicArea = geographicArea;
        this.specialCondition = specialCondition;
        this.transportType = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public PartnerId getId() {
        return this.id;
    }

    public Partner setId(PartnerId id) {
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

    public CommercialInfo getCommercialInfo() {
        return this.commercialInfo;
    }

    public Partner setCommercialInfo(CommercialInfo commercialInfo) {
        this.commercialInfo = commercialInfo;
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

    public TransportType getTransportType() {
        return this.transportType;
    }

    public Partner setTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public PartnerStatus getStatus() {
        return this.status;
    }

    public Partner setStatus(PartnerStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commerical info'" + commercialInfo + '\'' +
                ", geographicArea='" + geographicArea + '\'' +
                ", specialCondition='" + specialCondition + '\'' +
                ", transportType=" + transportType +
                ", status=" + status +
                '}';
    }
}
