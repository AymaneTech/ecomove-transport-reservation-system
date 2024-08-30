package com.wora.partner.application.usecases;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.dtos.requests.UpdatePartnerDto;

import java.util.UUID;

public interface UpdatePartnerUseCase {
    void execute(UUID id, UpdatePartnerDto dto);
}
