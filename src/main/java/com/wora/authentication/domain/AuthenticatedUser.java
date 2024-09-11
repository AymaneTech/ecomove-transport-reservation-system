package com.wora.authentication.domain;

import com.wora.client.domain.entities.Client;

public class AuthenticatedUser {
    private static Client client;

    public static Client getAuthenticated() {
        return client;
    }

    public static void authenticate(Client client) {
        AuthenticatedUser.client = client;
    }

    public static Boolean isAuthenticated() {
        return client != null;
    }
}
