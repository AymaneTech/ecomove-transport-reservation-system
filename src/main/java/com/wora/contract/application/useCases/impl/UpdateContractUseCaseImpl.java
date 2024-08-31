package com.wora.contract.application.useCases.impl;

import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.useCases.UpdateContractUseCase;
import com.wora.contract.domain.repositories.ContractRepository;

import java.util.UUID;

public class UpdateContractUseCaseImpl implements UpdateContractUseCase {
    private final ContractRepository repository;


    public UpdateContractUseCaseImpl(ContractRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, UpdateContractDto dto) {

    }
}
