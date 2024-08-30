package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.ChangePartnerStatusUseCase;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.repositories.PartnerRepository;

import java.util.UUID;

public class ChangePartnerStatusUseCaseImpl implements ChangePartnerStatusUseCase {

    private final PartnerRepository repository;

    public ChangePartnerStatusUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, String status) {
        repository.changeStatus(id, PartnerStatus.valueOf(status));
        System.out.println("partner status changed successfully");
    }
}
