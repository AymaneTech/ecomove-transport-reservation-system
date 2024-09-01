package com.wora.discount.domain.valueObjects;

import java.util.UUID;

public record DiscountId(UUID value) {

    public DiscountId() {
        this(UUID.randomUUID());
    }
}
