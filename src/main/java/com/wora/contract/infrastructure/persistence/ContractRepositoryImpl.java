package com.wora.contract.infrastructure.persistence;

import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.repositories.ContractRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ContractRepositoryImpl implements ContractRepository {

    @Override
    public List<Contract> findAll() {
        return List.of();
    }

    @Override
    public Optional<Contract> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void create(Contract partner) {

    }

    @Override
    public void update(UUID uuid, Contract partner) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public void changeStatus(UUID id, ContractStatus status) {

    }
}
