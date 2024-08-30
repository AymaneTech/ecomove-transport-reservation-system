package com.wora.contract.domain.repositories;

import com.wora.common.repositories.BaseRepository;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.enums.ContractStatus;

import java.util.UUID;

public interface ContractRepository extends BaseRepository<Contract, UUID> {
    void changeStatus(UUID id, ContractStatus status);
}
