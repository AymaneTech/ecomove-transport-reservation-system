package com.wora.discount.application.services;

import com.wora.discount.application.dtos.requests.CreateDiscountDto;
import com.wora.discount.application.dtos.requests.UpdateDiscountDto;
import com.wora.discount.application.dtos.responses.DiscountResponse;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.valueObjects.DiscountId;

import java.util.List;

public interface DiscountService {
    List<DiscountResponse> findAll();

    DiscountResponse findById(DiscountId id);

    void create(CreateDiscountDto dto);

    void update(DiscountId id, UpdateDiscountDto dto);

    void delete(DiscountId id);

    void changeStatus(DiscountId id, DiscountStatus status);

    Boolean existsById(DiscountId id);
}
