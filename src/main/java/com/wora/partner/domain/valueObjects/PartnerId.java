package com.wora.partner.domain.valueObjects;

import java.util.UUID;

public record PartnerId(UUID value) {

    public PartnerId() {
        this(UUID.randomUUID());
    }
}
