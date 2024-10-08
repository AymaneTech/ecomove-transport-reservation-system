package com.wora.authentication.domain;

import com.wora.authentication.domain.valueObjects.SessionId;
import com.wora.client.domain.entities.Client;

import java.time.LocalDateTime;

public class Session {
    private SessionId id;
    private Client client;
    private Boolean isLoggedIn;
    private LocalDateTime loggedAt;

    public Session() {
        this.id = new SessionId();
        this.isLoggedIn = false;
    }

    public Client getClient() {
        return client;
    }

    public Session setClient(Client client) {
        this.client = client;
        return this;
    }

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Session setLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        return this;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public Session setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
        return this;
    }

    public void authenticate(Client client) {
        this.client = client;
        this.isLoggedIn = true;
        this.loggedAt = LocalDateTime.now();
    }
}
