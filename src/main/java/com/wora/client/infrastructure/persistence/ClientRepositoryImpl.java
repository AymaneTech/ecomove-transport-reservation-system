package com.wora.client.infrastructure.persistence;

import com.wora.client.domain.entities.Client;
import com.wora.client.domain.repositories.ClientRepository;
import com.wora.contract.domain.exceptions.ContractNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {
    @Override
    public List<Client> findAll() {
        return List.of();
    }

    @Override
    public Optional<Client> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public void create(Client client) {

    }

    @Override
    public void update(UUID uuid, Client client) throws ContractNotFoundException {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Boolean existsById(UUID uuid) {
        return null;
    }

    @Override
    public Optional<Client> findByColumn(String columnName, String value) {
        return Optional.empty();
    }
}
