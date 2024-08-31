package com.wora.contract.application.useCases.impl;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.useCases.FindContractByIdUseCase;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.usecases.FindPartnerByIdUseCase;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;

import java.util.UUID;

public class FindContractByIdUseCaseImpl implements FindContractByIdUseCase {
    private final ContractRepository repository;
    private final FindPartnerByIdUseCase findPartnerByIdUseCase;
    private final ContractMapper mapper;

    public FindContractByIdUseCaseImpl(ContractRepository repository, FindPartnerByIdUseCase findPartnerByIdUseCase,
            ContractMapper mapper) {
        this.repository = repository;
        this.findPartnerByIdUseCase = findPartnerByIdUseCase;
        this.mapper = mapper;
    }

    @Override
    public ContractResponse execute(UUID id) {
        return repository.findById(id)
                .map(contract -> {
                    PartnerResponse partnerResponse;
                    try {
                        partnerResponse = findPartnerByIdUseCase.execute(contract.getPartnerId());
                        return mapper.map(contract, partnerResponse);
                    } catch (PartnerNotFoundException e) {
                        e.printStackTrace();
                    }
                })
                .orElseThrow();
    }
}
