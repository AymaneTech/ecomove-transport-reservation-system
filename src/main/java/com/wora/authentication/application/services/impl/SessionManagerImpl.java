package com.wora.authentication.application.services.impl;

import com.wora.authentication.domain.Session;
import com.wora.client.domain.entities.Client;

public class SessionManagerImpl {
    private final static Session session = new Session();

    public static void authenticate(Client client) {
        SessionManagerImpl.session.authenticate(client);
    }

    public static Session getSession() {
        return session;
    }

    public static Boolean isAuthenticated() {
        return session.isLoggedIn();
    }

    public static boolean isConnected(Client client) {
        return session.getClient().equals(client);
    }
}
