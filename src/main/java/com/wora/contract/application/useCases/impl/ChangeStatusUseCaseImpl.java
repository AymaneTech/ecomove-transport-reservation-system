package com.wora.contract.application.useCases.impl;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.application.useCases.ChangeStatusUseCase;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.repositories.ContractRepository;

import java.util.UUID;

public class ChangeStatusUseCaseImpl implements ChangeStatusUseCase {

    private final ContractRepository repository;

    public ChangeStatusUseCaseImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, ContractStatus status) {

    }
}
