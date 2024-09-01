package com.wora.contract.application.services;

import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.domain.enums.ContractStatus;

import java.util.List;
import java.util.UUID;

public interface ContractService {
    List<ContractResponse> findAll();

    ContractResponse findById(UUID id);

    void create(CreateContractDto dto);

    void update(UUID id, UpdateContractDto dto);

    void delete(UUID id);

    void changeStatus(UUID id, ContractStatus status);
}
