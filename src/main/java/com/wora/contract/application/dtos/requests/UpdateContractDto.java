package com.wora.contract.application.dtos.requests;

import com.wora.contract.domain.enums.ContractStatus;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.Date;

public record UpdateContractDto(
        String specialPrice,
        String agreementCondition,
        Boolean renewable,
        Date startedAt,
        Date endsAt,
        ContractStatus status,
        PartnerId partnerId
) {
}
