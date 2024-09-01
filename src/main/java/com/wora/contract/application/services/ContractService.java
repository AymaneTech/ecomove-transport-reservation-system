package com.wora.contract.application.services;

import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.valueObjects.ContractId;

import java.util.List;

public interface ContractService {
    List<ContractResponse> findAll();

    ContractResponse findById(ContractId id);

    void create(CreateContractDto dto);

    void update(ContractId id, UpdateContractDto dto);

    void delete(ContractId id);

    void changeStatus(ContractId id, ContractStatus status);
}
