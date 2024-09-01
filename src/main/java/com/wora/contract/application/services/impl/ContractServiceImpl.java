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
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.application.services.PartnerService;

import java.util.List;
import java.util.Objects;

public class ContractServiceImpl implements ContractService {
    private final ContractRepository repository;
    private final PartnerService partnerService;
    private final ContractMapper mapper;

    public ContractServiceImpl(ContractRepository repository, PartnerService partnerService, ContractMapper mapper) {
        this.repository = repository;
        this.partnerService = partnerService;
        this.mapper = mapper;
    }

    @Override
    public List<ContractResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(contract -> mapper.map(contract, partnerService.findById(contract.getPartnerId())))
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public ContractResponse findById(ContractId id) {
        return repository.findById(id.value())
                .map(contract -> mapper.map(contract, partnerService.findById(contract.getPartnerId())))
                .orElseThrow(() -> new ContractNotFoundException(id.value()));
    }

    @Override
    public void create(CreateContractDto dto) {
        final Contract contract = mapper.map(dto);
        repository.create(contract);
        System.out.println("The contract created successfully");
    }


    @Override
    public void update(ContractId id, UpdateContractDto dto) {
        repository.update(id.value(), mapper.map(dto, id.value()));
        System.out.println("Contract updated successfully");
    }

    @Override
    public void delete(ContractId id) {
        repository.delete(id.value());
        System.out.println("contract deleted successfully");
    }

    @Override
    public void changeStatus(ContractId id, ContractStatus status) {
        repository.changeStatus(id.value(), status);
        System.out.println("Contract status changed successfully");
    }
}