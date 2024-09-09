package com.wora.ticket.domain.repositories;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.ticket.domain.entities.Journey;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Strings;

import java.util.Optional;
import java.util.UUID;

public interface JourneyRepository extends BaseRepository<Journey, UUID> {
    Optional<Journey> findByStartAndEndStation(String startStationName, String endStationName);
}
