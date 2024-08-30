package com.wora.partner.application.usecases;

import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.domain.entities.Partner;

import java.util.List;

public interface FindAllPartnersUseCase {
    List<PartnerResponse> execute();
}
