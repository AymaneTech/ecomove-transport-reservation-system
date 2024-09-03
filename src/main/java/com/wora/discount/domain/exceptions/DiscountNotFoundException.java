package com.wora.discount.domain.exceptions;

import java.util.UUID;

public class DiscountNotFoundException extends  RuntimeException{
    private final UUID id;

    public DiscountNotFoundException(UUID id) {
        super("discount with id " + id + " not found");
        this.id = id;
    }
}
