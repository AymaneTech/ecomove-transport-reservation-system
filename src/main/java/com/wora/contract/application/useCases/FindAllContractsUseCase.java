package com.wora.contract.application.useCases;

import com.wora.contract.application.dtos.responses.ContractResponse;

import java.util.List;

public interface FindAllContractsUseCase {
    List<ContractResponse> execute();
}
