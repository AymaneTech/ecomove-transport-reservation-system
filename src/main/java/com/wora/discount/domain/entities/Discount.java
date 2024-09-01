package com.wora.discount.domain.entities;

import com.wora.discount.domain.enums.OfferStatus;
import com.wora.discount.domain.valueObjects.DiscountId;
import com.wora.discount.domain.valueObjects.Reduction;

import java.util.Date;

public class Discount {
    private DiscountId id;
    private String name;
    private String description;
    private String conditions;
    private Reduction reduction;
    private Date startedAt;
    private Date endsAt;
    private OfferStatus status;

    
}
