package com.wora.ticket.application.services.impl;

import com.wora.ticket.application.dtos.requests.CreateStationDto;
import com.wora.ticket.application.dtos.requests.UpdateStationDto;
import com.wora.ticket.application.dtos.responses.StationResponse;
import com.wora.ticket.application.mappers.StationMapper;
import com.wora.ticket.application.services.StationService;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.exceptions.StationNotFoundException;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.List;
import java.util.Optional;

public class StationServiceImpl implements StationService {
    private final StationRepository repository;
    private final StationMapper mapper;

    public StationServiceImpl(StationRepository repository, StationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<StationResponse> findAll() {
        return repository.findAll()
                .stream().map(mapper::map)
                .toList();
    }

    @Override
    public StationResponse findById(StationId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new StationNotFoundException(id.value()));
    }

    @Override
    public void create(CreateStationDto dto) {
        repository.create(mapper.map(dto));
    }

    @Override
    public void update(StationId id, UpdateStationDto dto) {
        repository.update(id.value(), mapper.map(dto, id));
    }

    @Override
    public void delete(StationId id) {
        repository.delete(id.value());
    }

    @Override
    public Boolean existsById(StationId id) {
        return repository.existsById(id.value());
    }

    @Override
    public Station findByCityName(String cityName) {
        return repository.findByCityName(cityName)
                .orElseThrow(() -> new StationNotFoundException(cityName));
    }

    public Station firstOrCreate(String cityName) {
        Optional<Station> station = repository.findByCityName(cityName);

        if (station.isEmpty()) {
            this.create(
                    new CreateStationDto(cityName, cityName)
            );
            return this.findByCityName(cityName);
        }
        return station.get();
    }
}
