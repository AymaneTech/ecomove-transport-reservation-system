package com.wora.contract.application.useCases.impl;

import java.util.List;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.useCases.FindAllContractsUseCase;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.usecases.FindPartnerByIdUseCase;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;

public class FindAllContractsUseCaseImpl implements FindAllContractsUseCase {
    private final ContractRepository repository;
    private final FindPartnerByIdUseCase findPartnerByIdUseCase;
    private final ContractMapper mapper;

    public FindAllContractsUseCaseImpl(ContractRepository repository, FindPartnerByIdUseCase findPartnerByIdUseCase,
            ContractMapper mapper) {
        this.repository = repository;
        this.findPartnerByIdUseCase = findPartnerByIdUseCase;
        this.mapper = mapper;
    }

    @Override
    public List<ContractResponse> execute() {
        return repository.findAll()
                .stream()
                .map(contarct -> {
                    try {
                        final PartnerResponse partnerResponse = findPartnerByIdUseCase.execute(contarct.getPartnerId());
                        return mapper.map(contarct, partnerResponse);
                    } catch (PartnerNotFoundException e) {
                        e.getStackTrace();
                    }
                })
                .toList();
    }
}
