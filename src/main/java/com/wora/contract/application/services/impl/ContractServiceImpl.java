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
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;

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
                .map(contract -> {
                    try {
                        final PartnerResponse partnerResponse = partnerService.findById(contract.getPartnerId());
                        return mapper.map(contract, partnerResponse);
                    } catch (PartnerNotFoundException e) {
                        return mapper.map(contract, null);
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public ContractResponse findById(ContractId id) {
        return repository.findById(id.value())
                .map(contract -> {
                    try {
                        return mapper.map(contract, partnerService.findById(contract.getPartnerId()));
                    } catch (PartnerNotFoundException e) {
                        return mapper.map(contract, null);
                    }
                })
                .orElseThrow(() -> new ContractNotFoundException(id.value()));
    }

    @Override
    public void create(CreateContractDto dto) {
        final Contract contract = mapper.map(dto);
        repository.create(contract);
    }


    @Override
    public void update(ContractId id, UpdateContractDto dto) {
        repository.update(id.value(), mapper.map(dto, id.value()));
    }

    @Override
    public void delete(ContractId id) {
        repository.delete(id.value());
    }

    @Override
    public void changeStatus(ContractId id, ContractStatus status) {
        repository.changeStatus(id.value(), status);
    }

    @Override
    public Boolean existsById(ContractId id) {
        return repository.existsById(id.value());
    }
}