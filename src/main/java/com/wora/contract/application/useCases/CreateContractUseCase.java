package com.wora.contract.application.useCases;

import com.wora.contract.application.dtos.requests.CreateContractDto;

public interface CreateContractUseCase {
    void execute(CreateContractDto dto);
}
