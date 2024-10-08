package com.wora.client.application.services.impl;

import com.wora.client.application.dtos.requests.CreateClientDto;
import com.wora.client.application.dtos.requests.UpdateClientDto;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.client.application.mappers.ClientMapper;
import com.wora.client.application.services.ClientService;
import com.wora.client.domain.entities.Client;
import com.wora.client.domain.exceptions.ClientNotFoundException;
import com.wora.client.domain.repositories.ClientRepository;
import com.wora.client.domain.valueObjects.ClientId;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientMapper mapper;

    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ClientResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public ClientResponse findById(ClientId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new ClientNotFoundException(id.value()));
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return repository.findByColumn("email", email);
    }

    @Override
    public void create(CreateClientDto dto) {
        repository.create(mapper.map(dto));
    }

    @Override
    public void update(ClientId id, UpdateClientDto dto) {
        repository.update(id.value(), mapper.map(dto, id));
    }

    @Override
    public void delete(ClientId id) {
        repository.delete(id.value());
    }

    @Override
    public Boolean existsById(ClientId id) {
        return repository.existsById(id.value());
    }
}
