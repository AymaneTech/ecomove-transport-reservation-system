package com.wora.contract.application.useCases;

import com.wora.contract.domain.enums.ContractStatus;

import java.util.UUID;

public interface ChangeStatusUseCase {
    void execute(UUID id, ContractStatus status);
}
