package com.wora.partner.application.services.impl;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.dtos.requests.UpdatePartnerDto;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.List;

public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public PartnerServiceImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<PartnerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public PartnerResponse findById(PartnerId id) throws PartnerNotFoundException {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new PartnerNotFoundException(id.value()));
    }

    @Override
    public void create(CreatePartnerDto dto) {
        final Partner partner = mapper.map(dto);
        repository.create(partner);
    }

    @Override
    public void update(PartnerId id, UpdatePartnerDto dto) {
        repository.update(id.value(), mapper.map(dto, id.value()));
    }

    @Override
    public void delete(PartnerId id) {
        repository.delete(id.value());
    }

    @Override
    public void changeStatus(PartnerId id, PartnerStatus status) {
        repository.changeStatus(id.value(), status);
    }

    @Override
    public Boolean existsById(PartnerId id) {
        return repository.existsById(id.value());
    }
}
