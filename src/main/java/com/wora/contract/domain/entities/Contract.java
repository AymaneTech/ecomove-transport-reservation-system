package com.wora.contract.domain.entities;

import com.wora.common.models.AbstractEntity;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.Date;

public class Contract extends AbstractEntity<ContractId> {
    private ContractId id;
    private String specialPrice;
    private String agreementCondition;
    private Boolean renewable;
    private Date startedAt;
    private Date endsAt;
    private ContractStatus status;
    private PartnerId partnerId;

    Contract() {
    }

    public Contract(ContractId id, String specialPrice, String agreementCondition, Boolean renewable, Date startedAt, Date endsAt, ContractStatus status, PartnerId partnerId) {
        this.id = id;
        this.specialPrice = specialPrice;
        this.agreementCondition = agreementCondition;
        this.renewable = renewable;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.status = status;
        this.partnerId = partnerId;
    }

    @Override
    public ContractId getId() {
        return id;
    }

    public Contract setId(ContractId id) {
        this.id = id;
        return this;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public Contract setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    public String getAgreementCondition() {
        return agreementCondition;
    }

    public Contract setAgreementCondition(String agreementCondition) {
        this.agreementCondition = agreementCondition;
        return this;
    }

    public Boolean getRenewable() {
        return renewable;
    }

    public Contract setRenewable(Boolean renewable) {
        this.renewable = renewable;
        return this;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Contract setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public Contract setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
        return this;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public Contract setStatus(ContractStatus status) {
        this.status = status;
        return this;
    }


    public PartnerId getPartnerId() {
        return partnerId;
    }

    public Contract setPartnerId(PartnerId partnerId) {
        this.partnerId = partnerId;
        return this;
    }
}
