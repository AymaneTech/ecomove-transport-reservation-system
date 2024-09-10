package com.wora.ticket.application.services.impl;

import com.wora.ticket.application.dtos.requests.CreateJourneyDto;
import com.wora.ticket.application.dtos.requests.JourneyDto;
import com.wora.ticket.application.dtos.requests.UpdateJourneyDto;
import com.wora.ticket.application.dtos.responses.JourneyResponse;
import com.wora.ticket.application.mappers.JourneyMapper;
import com.wora.ticket.application.services.JourneyService;
import com.wora.ticket.application.services.StationService;
import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.exceptions.JourneyNotFoundException;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.domain.valueObjects.JourneyId;

import java.util.List;
import java.util.Optional;

public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository repository;
    private final StationService stationService;
    private final JourneyMapper mapper;

    public JourneyServiceImpl(JourneyRepository repository, StationService stationService, JourneyMapper mapper) {
        this.repository = repository;
        this.stationService = stationService;
        this.mapper = mapper;
    }

    @Override
    public List<JourneyResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public JourneyResponse findById(JourneyId id) {
        return repository.findById(id.value())
                .map(mapper::map)
                .orElseThrow(() -> new JourneyNotFoundException(id.value()));
    }

    @Override
    public void create(CreateJourneyDto dto) {
        repository.create(mapper.map(
                dto,
                stationService.firstOrCreate(dto.startCityName()),
                stationService.firstOrCreate(dto.endCityName()))
        );
    }

    @Override
    public void update(JourneyId id, UpdateJourneyDto dto) {
        repository.update(
                id.value(),
                mapper.map(
                        id,
                        dto,
                        stationService.findByCityName(dto.startCityName()),
                        stationService.findByCityName(dto.endCityName())
                )
        );
    }

    @Override
    public void delete(JourneyId id) {
        repository.delete(id.value());
    }

    @Override
    public Boolean existsById(JourneyId id) {
        return repository.existsById(id.value());
    }

    @Override
    public Journey findByStartAndEndStation(JourneyDto dto) {
        Optional<Journey> journal = repository.findByStartAndEndStation(dto.startStatioName(), dto.endStatioName())
                .or(() -> {
                    final Journey journey = new Journey(
                            new JourneyId(),
                            stationService.firstOrCreate(dto.startStatioName()),
                            stationService.firstOrCreate(dto.endStatioName()),
                           0.0
                    );
                    this.repository.create(journey);
                    return Optional.of(journey);
                });
        return journal.get();
    }
}
