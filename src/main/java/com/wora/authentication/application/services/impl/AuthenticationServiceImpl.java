package com.wora.authentication.application.services.impl;

import com.wora.authentication.application.dtos.requests.LoginClientDto;
import com.wora.authentication.application.dtos.requests.RegisterClientDto;
import com.wora.authentication.application.services.AuthenticationService;
import com.wora.client.application.dtos.requests.CreateClientDto;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.application.mappers.ClientMapper;
import com.wora.client.application.services.ClientService;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.exceptions.ClientNotFoundException;
import com.wora.client.domain.valueObjects.Name;

import java.util.Optional;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final ClientService service;
    private final ClientMapper mapper;

    public AuthenticationServiceImpl(ClientService service, ClientMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ClientResponse register(RegisterClientDto dto) {
        service.create(new CreateClientDto(
                new Name(dto.firstName(), dto.lastName()),
                dto.email(),
                dto.phone()
        ));
        return authenticate(dto.email());
    }

    @Override
    public ClientResponse login(LoginClientDto dto) {
        return authenticate(dto.email());
    }

    private ClientResponse authenticate(String email) {
        Optional<Client> client = service.findByEmail(email);
        client.ifPresent(SessionManagerImpl::authenticate);
        return client
                .map(mapper::map)
                .orElseThrow(() -> new ClientNotFoundException(email));
    }
}
