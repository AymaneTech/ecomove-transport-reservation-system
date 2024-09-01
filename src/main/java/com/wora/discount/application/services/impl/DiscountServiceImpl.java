package com.wora.discount.application.services.impl;

import com.wora.contract.application.services.ContractService;
import com.wora.discount.application.dtos.requests.CreateDiscountDto;
import com.wora.discount.application.dtos.requests.UpdateDiscountDto;
import com.wora.discount.application.dtos.responses.DiscountResponseDto;
import com.wora.discount.application.mappers.DiscountMapper;
import com.wora.discount.application.services.DiscountService;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.repositories.DiscountRepository;
import com.wora.discount.domain.valueObjects.DiscountId;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository repository;
    private final ContractService contractService;
    private final DiscountMapper mapper;

    public DiscountServiceImpl(DiscountRepository repository, ContractService contractService, DiscountMapper mapper) {
        this.repository = repository;
        this.contractService = contractService;
        this.mapper = mapper;
    }

    @Override
    public List<DiscountResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(discount -> mapper.map(discount, contractService.findById(discount.getContractId())))
                .toList();
    }

    @Override
    public DiscountResponseDto findById(DiscountId id) {
        return repository.findById(id.value())
                .map(discount -> mapper.map(discount, contractService.findById(discount.getContractId())))
                .orElseThrow();
    }

    @Override
    public void create(CreateDiscountDto dto) {
        repository.create(mapper.map(dto));
    }

    @Override
    public void update(DiscountId id, UpdateDiscountDto dto) {
        repository.update(id.value(), mapper.map(dto, id));
    }

    @Override
    public void delete(DiscountId id) {
        repository.delete(id.value());
    }

    @Override
    public void changeStatus(DiscountId id, DiscountStatus status) {
        repository.changeStatus(id.value(), status);
    }
}
