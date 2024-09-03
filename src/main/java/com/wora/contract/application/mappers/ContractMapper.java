package com.wora.contract.application.mappers;

import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.domain.entities.Contract;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.partner.application.dtos.responses.PartnerResponse;

import java.util.UUID;

public class ContractMapper {

    public Contract map(CreateContractDto dto) {
        return new Contract(
                new ContractId(),
                dto.specialPrice(),
                dto.agreementCondition(),
                dto.renewable(),
                dto.startedAt(),
                dto.endsAt(),
                dto.status(),
                dto.partnerId()
        );
    }

    public Contract map(UpdateContractDto dto, UUID id) {
        return new Contract(
                new ContractId(id),
                dto.specialPrice(),
                dto.agreementCondition(),
                dto.renewable(),
                dto.startedAt(),
                dto.endsAt(),
                dto.status(),
                dto.partnerId()
        );
    }

    public ContractResponse map(Contract contract, PartnerResponse partnerResponse) {
        return new ContractResponse(
                contract.getId(),
                contract.getSpecialPrice(),
                contract.getAgreementCondition(),
                contract.getRenewable(),
                contract.getStartedAt(),
                contract.getEndsAt(),
                contract.getStatus(),
                partnerResponse,
                contract.getCreatedAt(),
                contract.getUpdatedAt()
        );
    }
}