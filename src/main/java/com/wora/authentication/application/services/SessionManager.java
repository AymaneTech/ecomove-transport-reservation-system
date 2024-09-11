package com.wora.authentication.application.services;

import com.wora.client.domain.entities.Client;

public interface SessionManager {
    void authenticate(Client client);
}
