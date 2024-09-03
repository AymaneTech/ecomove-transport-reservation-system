package com.wora.discount.application.dtos.requests;

import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.valueObjects.Reduction;

import java.util.Date;

public record CreateDiscountDto(
        ContractId contractId,
        String name,
        String description,
        String conditions,
        Reduction reduction,
        Date startedAt,
        Date endsAt,
        DiscountStatus status
) {
}
