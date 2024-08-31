package com.wora.partner.application.usecases;

import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;
import com.wora.partner.domain.valueObjects.PartnerId;

public interface FindPartnerByIdUseCase {
    PartnerResponse execute(PartnerId id) throws PartnerNotFoundException;
}
