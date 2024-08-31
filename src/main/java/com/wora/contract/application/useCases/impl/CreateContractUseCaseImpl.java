package com.wora.contract.application.useCases.impl;

import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.useCases.CreateContractUseCase;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.partner.application.usecases.FindPartnerByIdUseCase;

public class CreateContractUseCaseImpl implements CreateContractUseCase {

    private final ContractRepository repository;
    private final ContractMapper mapper;

    public CreateContractUseCaseImpl(ContractRepository repository, FindPartnerByIdUseCase findPartnerByIdUseCase, ContractMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void execute(CreateContractDto dto) {
        final Contract contract = mapper.map(dto);
        repository.create(contract);
        System.out.println("the contract created successfully");
    }
}
