package com.wora.contract.domain.valueObjects;

import java.util.UUID;

public record ContractId(UUID value) {

    public ContractId() {
        this(UUID.randomUUID());
    }
}
