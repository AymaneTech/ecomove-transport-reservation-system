package com.wora.authentication.domain.valueObjects;

import com.wora.authentication.domain.Session;

import java.util.UUID;

public record SessionId(UUID value) {
    public SessionId() {
        this(UUID.randomUUID());
    }
}
