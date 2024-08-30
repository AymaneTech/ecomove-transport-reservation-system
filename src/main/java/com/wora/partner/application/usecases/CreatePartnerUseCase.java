package com.wora.partner.application.usecases;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;

public interface CreatePartnerUseCase {
    void execute(CreatePartnerDto dto);
}
