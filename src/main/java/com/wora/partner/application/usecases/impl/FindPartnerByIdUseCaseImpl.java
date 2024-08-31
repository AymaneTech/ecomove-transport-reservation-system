package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.FindPartnerByIdUseCase;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.UUID;

public class FindPartnerByIdUseCaseImpl implements FindPartnerByIdUseCase {

    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public FindPartnerByIdUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PartnerResponse execute(PartnerId id) throws PartnerNotFoundException {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new PartnerNotFoundException(id.value()));
    }
}
