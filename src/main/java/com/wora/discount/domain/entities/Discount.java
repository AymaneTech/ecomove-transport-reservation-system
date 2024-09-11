package com.wora.discount.domain.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.valueObjects.DiscountId;
import com.wora.discount.domain.valueObjects.Reduction;

import java.time.LocalDateTime;
import java.util.Date;

public class Discount extends AbstractEntity<DiscountId> {
    private DiscountId id;
    private ContractId contractId;
    private String name;
    private String description;
    private String conditions;
    private Reduction reduction;
    private Date startedAt;
    private Date endsAt;
    private DiscountStatus status;

    public Discount(DiscountId id, ContractId contractId, String name, String description, String conditions, Reduction reduction, Date startedAt, Date endsAt, DiscountStatus status) {
        this.id = id;
        this.contractId = contractId;
        this.name = name;
        this.description = description;
        this.conditions = conditions;
        this.reduction = reduction;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.status = status;
    }

    public Discount(DiscountId id, ContractId contractId, String name, String description, String conditions, Reduction reduction, Date startedAt, Date endsAt, DiscountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.contractId = contractId;
        this.name = name;
        this.description = description;
        this.conditions = conditions;
        this.reduction = reduction;
        this.startedAt = startedAt;
        this.endsAt = endsAt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public DiscountId getId() {
        return id;
    }

    public Discount setId(DiscountId id) {
        this.id = id;
        return this;
    }

    public ContractId getContractId() {
        return contractId;
    }

    public void setContractId(ContractId contractId) {
        this.contractId = contractId;
    }

    public String getName() {
        return name;
    }

    public Discount setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Discount setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getConditions() {
        return conditions;
    }

    public Discount setConditions(String conditions) {
        this.conditions = conditions;
        return this;
    }

    public Reduction getReduction() {
        return reduction;
    }

    public Discount setReduction(Reduction reduction) {
        this.reduction = reduction;
        return this;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public Discount setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public Discount setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
        return this;
    }

    public DiscountStatus getStatus() {
        return status;
    }

    public Discount setStatus(DiscountStatus status) {
        this.status = status;
        return this;
    }
}
