package com.wora.partner.application.usecases;

import java.util.UUID;

public interface ChangePartnerStatusUseCase {
    void execute(UUID id, String status);
}
