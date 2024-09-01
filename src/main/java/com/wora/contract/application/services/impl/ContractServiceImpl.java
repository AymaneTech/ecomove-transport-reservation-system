package com.wora.contract.application.services.impl;

import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.exceptions.ContractNotFoundException;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.usecases.FindPartnerByIdUseCase;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ContractServiceImpl implements ContractService {
    private final ContractRepository repository;
    private final FindPartnerByIdUseCase findPartnerByIdUseCase;
    private final ContractMapper mapper;

    public ContractServiceImpl(ContractRepository repository, FindPartnerByIdUseCase findPartnerByIdUseCase, ContractMapper mapper) {
        this.repository = repository;
        this.findPartnerByIdUseCase = findPartnerByIdUseCase;
        this.mapper = mapper;
    }

    @Override
    public List<ContractResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(contract -> {
                    try {
                        final PartnerResponse partnerResponse = findPartnerByIdUseCase.execute(contract.getPartnerId());
                        return mapper.map(contract, partnerResponse);
                    } catch (PartnerNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public ContractResponse findById(UUID id) {
        return repository.findById(id)
                .map(contract -> {
                    try {
                        PartnerResponse partnerResponse = findPartnerByIdUseCase.execute(contract.getPartnerId());
                        return mapper.map(contract, partnerResponse);
                    } catch (PartnerNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new ContractNotFoundException(id));
    }

    @Override
    public void create(CreateContractDto dto) {
        final Contract contract = mapper.map(dto);
        repository.create(contract);
        System.out.println("The contract created successfully");
    }


    @Override
    public void update(UUID id, UpdateContractDto dto) {
        repository.update(id, mapper.map(dto, id));
        System.out.println("Contract updated successfully");
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
        System.out.println("contract deleted successfully");
    }

    @Override
    public void changeStatus(UUID id, ContractStatus status) {
        repository.changeStatus(id, status);
        System.out.println("Contract status changed successfully");
    }
}