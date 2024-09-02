package com.wora.partner.application.services;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.dtos.requests.UpdatePartnerDto;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.List;

public interface PartnerService {

    List<PartnerResponse> findAll();

    PartnerResponse findById(PartnerId id);

    void create(CreatePartnerDto dto);

    void update(PartnerId id, UpdatePartnerDto dto);

    void delete(PartnerId id);

    void changeStatus(PartnerId id, PartnerStatus status);

    Boolean existsById(PartnerId id);
}
