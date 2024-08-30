package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.dtos.requests.UpdatePartnerDto;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.UpdatePartnerUseCase;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.repositories.PartnerRepository;

import java.util.UUID;

public class UpdatePartnerUseCaseImpl implements UpdatePartnerUseCase {
    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public UpdatePartnerUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void execute(UUID id, UpdatePartnerDto dto) {
        final Partner partner = mapper.map(dto, id);
        repository.update(id, partner);
        System.out.println("partner update successfully");
    }
}
