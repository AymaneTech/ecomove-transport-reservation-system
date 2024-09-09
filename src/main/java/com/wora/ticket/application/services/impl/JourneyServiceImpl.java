package com.wora.ticket.application.services.impl;

import com.wora.journey.application.mappers.JourneyMapper;
import com.wora.ticket.application.dtos.requests.CreateJourneyDto;
import com.wora.ticket.application.dtos.requests.UpdateJourneyDto;
import com.wora.ticket.application.dtos.responses.JourneyResponse;
import com.wora.ticket.application.services.JourneyService;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.exceptions.JourneyNotFoundException;
import com.wora.ticket.domain.exceptions.StationNotFoundException;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.domain.valueObjects.JourneyId;

import java.util.List;
import java.util.UUID;

public class JourneyServiceImpl implements JourneyService {
    private final JourneyRepository repository;
    private final StationRepository stationRepository;
    private final JourneyMapper mapper;

    public JourneyServiceImpl(JourneyRepository repository, StationRepository stationRepository, JourneyMapper mapper) {
        this.repository = repository;
        this.stationRepository = stationRepository;
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
        final List<Station> startAndEndStations = findStartAndEndStations(dto.startId().value(), dto.endId().value());
        repository.create(mapper.map(dto, startAndEndStations.get(0), startAndEndStations.get(1)));
    }

    @Override
    public void update(JourneyId id, UpdateJourneyDto dto) {
        final List<Station> startAndEndStations = findStartAndEndStations(dto.startId().value(), dto.endId().value());
        repository.update(
                id.value(),
                mapper.map(id, dto, startAndEndStations.get(0), startAndEndStations.get(1))
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

    private List<Station> findStartAndEndStations(UUID startId, UUID endId) {
        return List.of(
                stationRepository.findById(startId)
                        .orElseThrow(() -> new StationNotFoundException(startId)),
                stationRepository.findById(endId)
                        .orElseThrow(() -> new StationNotFoundException(endId))
        );
    }
}
