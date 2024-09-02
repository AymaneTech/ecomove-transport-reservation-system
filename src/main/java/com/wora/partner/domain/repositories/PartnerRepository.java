package com.wora.partner.domain.repositories;

import com.wora.common.infrastructure.persistence.BaseRepository;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.enums.PartnerStatus;

import java.util.UUID;

public interface PartnerRepository extends BaseRepository<Partner, UUID> {
    void changeStatus(UUID id, PartnerStatus status);
}
