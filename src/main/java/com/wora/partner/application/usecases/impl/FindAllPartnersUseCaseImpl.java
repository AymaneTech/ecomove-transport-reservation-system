package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.FindAllPartnersUseCase;
import com.wora.partner.domain.repositories.PartnerRepository;

import java.util.List;

public class FindAllPartnersUseCaseImpl implements FindAllPartnersUseCase {

    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public FindAllPartnersUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PartnerResponse> execute() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }
}
