package com.wora.ticket.domain.repositories;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.ticket.domain.entities.Station;

import java.util.Optional;
import java.util.UUID;

public interface StationRepository extends BaseRepository<Station, UUID> {

    Optional<Station> findByCityName(String cityName);
}
