package com.wora.authentication.application.services;

import com.wora.client.application.dtos.requests.CreateClientDto;
import com.wora.client.application.dtos.requests.LoginClient;
import com.wora.client.domain.entities.Client;

import java.util.Optional;

public interface authenticationService {
    Optional<Client> register(CreateClientDto dto);

    Optional<Client> login(LoginClient dto);
}
