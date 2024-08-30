package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.CreatePartnerUseCase;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.repositories.PartnerRepository;

public class CreatePartnerUseCaseImpl implements CreatePartnerUseCase{
    private final PartnerRepository repository;
    private final PartnerMapper mapper;



    public CreatePartnerUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void execute(CreatePartnerDto dto) {
        final Partner partner = mapper.map(dto);
        repository.create(partner);
        System.out.println("partner created successfully");;
    }
}
