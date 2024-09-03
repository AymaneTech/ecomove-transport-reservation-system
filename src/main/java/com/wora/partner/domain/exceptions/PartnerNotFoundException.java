package com.wora.partner.domain.exceptions;

import java.util.UUID;

public class PartnerNotFoundException extends Throwable {
    private final UUID id;
    public PartnerNotFoundException(UUID id) {
        super("No partner found with id: "+ id);
        this.id  = id ;
    }
}
