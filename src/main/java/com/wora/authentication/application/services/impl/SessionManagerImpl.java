package com.wora.authentication.application.services.impl;

import com.wora.authentication.application.services.SessionManager;
import com.wora.authentication.domain.Session;
import com.wora.client.domain.entities.Client;

public class SessionManagerImpl implements SessionManager {
    private final static Session session = new Session();

    @Override
    public void authenticate(Client client) {
        SessionManagerImpl.session.authenticate(client);
    }
}
