package com.wora.partner.application.usecases;

import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;

import java.util.UUID;

public interface FindPartnerByIdUseCase {
    PartnerResponse execute(UUID id) throws PartnerNotFoundException;
}
