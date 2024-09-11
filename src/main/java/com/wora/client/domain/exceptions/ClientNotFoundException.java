package com.wora.client.domain.exceptions;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {
    private final UUID id;
    private final String email;

    public ClientNotFoundException(UUID id) {
        super("client with id "+ id + " not found");
        this.id = id;
        this.email = null;
    }

    public ClientNotFoundException(String email) {
        super("client with email " + email + " not found");
        this.email = email;
        this.id = null;
    }
}
