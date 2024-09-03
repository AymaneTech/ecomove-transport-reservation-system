package com.wora.discount.application.mappers;

import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.discount.application.dtos.requests.CreateDiscountDto;
import com.wora.discount.application.dtos.requests.UpdateDiscountDto;
import com.wora.discount.application.dtos.responses.DiscountResponse;
import com.wora.discount.domain.entities.Discount;
import com.wora.discount.domain.valueObjects.DiscountId;

public class DiscountMapper {

    public Discount map(CreateDiscountDto dto) {
        return new Discount(
                new DiscountId(),
                dto.contractId(),
                dto.name(),
                dto.description(),
                dto.conditions(),
                dto.reduction(),
                dto.startedAt(),
                dto.endsAt(),
                dto.status()
        );
    }

    public Discount map(UpdateDiscountDto dto, DiscountId id) {
        return new Discount(
                id,
                dto.contractId(),
                dto.name(),
                dto.description(),
                dto.conditions(),
                dto.reduction(),
                dto.startedAt(),
                dto.endsAt(),
                dto.status()
        );
    }

    public DiscountResponse map(Discount discount, ContractResponse contractResponse) {
        return new DiscountResponse(
                discount.getId(),
                discount.getName(),
                discount.getDescription(),
                discount.getConditions(),
                discount.getReduction(),
                discount.getStartedAt(),
                discount.getEndsAt(),
                discount.getStatus(),
                contractResponse,
                discount.getCreatedAt(),
                discount.getUpdatedAt()
        );
    }
}