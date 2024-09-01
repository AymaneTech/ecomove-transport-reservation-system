package com.wora.discount.domain.repositories;

import com.wora.common.contracts.BaseRepository;
import com.wora.discount.domain.entities.Discount;
import com.wora.discount.domain.enums.DiscountStatus;

import java.util.UUID;

public interface DiscountRepository extends BaseRepository<Discount, UUID> {
    void changeStatus(UUID id, DiscountStatus status);
}
