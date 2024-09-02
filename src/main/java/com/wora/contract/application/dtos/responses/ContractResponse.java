package com.wora.contract.application.dtos.responses;

import com.wora.contract.domain.enums.ContractStatus;
import com.wora.partner.application.dtos.responses.PartnerResponse;

import java.util.Date;
import java.util.UUID;

public record ContractResponse(
        UUID id,
        String specialPrice,
        String agreementCondition,
        Boolean renewable,
        Date startedAt,
        Date endsAt,
        ContractStatus status,
        PartnerResponse partner
) {
}