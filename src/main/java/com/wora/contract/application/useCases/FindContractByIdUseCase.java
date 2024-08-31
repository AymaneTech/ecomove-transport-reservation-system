package com.wora.contract.application.useCases;

import com.wora.contract.application.dtos.responses.ContractResponse;

import java.util.UUID;

public interface FindContractByIdUseCase {
    ContractResponse execute(UUID id);
}
