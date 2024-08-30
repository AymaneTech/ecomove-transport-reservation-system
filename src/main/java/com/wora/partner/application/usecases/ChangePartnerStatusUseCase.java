package com.wora.partner.application.usecases;

import com.wora.partner.domain.enums.PartnerStatus;

import java.util.UUID;

public interface ChangePartnerStatusUseCase {
    void execute(UUID id, String status);
}
