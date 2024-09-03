package com.wora.contract.domain.exceptions;

import com.wora.contract.domain.entities.Contract;

import java.util.UUID;

public class ContractNotFoundException extends RuntimeException {
    private final UUID id;

    public ContractNotFoundException(UUID id) {
        super("could not found contract with id " + id);
        this.id = id;
    }
}
