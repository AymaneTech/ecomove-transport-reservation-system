package com.wora.discount.application.dtos.responses;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.valueObjects.DiscountId;
import com.wora.discount.domain.valueObjects.Reduction;

import java.util.Date;

public record DiscountResponseDto(
        DiscountId id,
        String name,
        String description,
        String conditions,
        Reduction reduction,
        Date startedAt,
        Date endsAt,
        DiscountStatus status,
        ContractResponse contract,
        Date createdAt,
        Date updatedAt
) {
}
