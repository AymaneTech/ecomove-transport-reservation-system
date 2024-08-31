package com.wora.contract.application.useCases;

import com.wora.contract.application.dtos.requests.UpdateContractDto;

import java.util.UUID;

public interface UpdateContractUseCase {
    void execute(UUID id, UpdateContractDto dto);
}
