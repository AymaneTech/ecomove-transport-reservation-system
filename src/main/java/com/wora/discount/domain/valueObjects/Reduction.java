package com.wora.discount.domain.valueObjects;

import com.wora.discount.domain.enums.ReductionType;

public record Reduction(
        Float value,
        ReductionType type
) {
}
