package com.wora.partner.application.usecases.impl;

import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.usecases.DeletePartnerUseCase;
import com.wora.partner.domain.repositories.PartnerRepository;

import java.util.UUID;

public class DeletePartnerUseCaseImpl implements DeletePartnerUseCase {

    private final PartnerRepository repository;

    public DeletePartnerUseCaseImpl(PartnerRepository repository, PartnerMapper mapper) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id) {
        repository.delete(id);
        System.out.println("partner deleted successfully");
    }
}
