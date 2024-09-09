package com.wora.ticket.domain.repositories;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.ticket.domain.entities.Journey;

import java.util.UUID;

public interface JourneyRepository extends BaseRepository<Journey, UUID> {
}
