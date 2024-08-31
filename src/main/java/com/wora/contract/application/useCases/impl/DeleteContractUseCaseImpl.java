package com.wora.contract.application.useCases.impl;

import com.wora.contract.application.useCases.DeleteContractUseCase;
import com.wora.contract.domain.repositories.ContractRepository;

import java.util.UUID;

public class DeleteContractUseCaseImpl implements DeleteContractUseCase {
    private final ContractRepository repository;

    public DeleteContractUseCaseImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id) {

    }
}
