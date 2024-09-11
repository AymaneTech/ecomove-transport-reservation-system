package com.wora.client.domain.entities;

import com.wora.client.domain.valueObjects.ClientId;
import com.wora.client.domain.valueObjects.Name;
import com.wora.common.domain.AbstractEntity;

import java.time.LocalDateTime;

public class Client extends AbstractEntity<ClientId> {
    private ClientId id;
    private Name name;
    private String email;
    private String phone;

    public Client(ClientId id, Name name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Client(ClientId id, Name name, String email, String phone, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this(id, name, email, phone);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    @Override
    public ClientId getId() {
        return id;
    }

    public Client setId(ClientId id) {
        this.id = id;
        return this;
    }

    public Name getName() {
        return name;
    }

    public Client setName(Name name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Client setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Client setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
