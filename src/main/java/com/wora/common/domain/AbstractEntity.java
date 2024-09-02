package com.wora.common.domain;

import java.util.Date;

public abstract class AbstractEntity<ID> {

    protected Date createdAt;
    protected Date updatedAt;
    protected Date deletedAt;

    public abstract ID getId();

    public Date getCreatedAt() {
        return createdAt;
    }

    public AbstractEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public AbstractEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public AbstractEntity setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
