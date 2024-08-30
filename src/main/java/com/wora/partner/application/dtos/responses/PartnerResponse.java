package com.wora.partner.application.dtos.responses;

import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.Date;
import java.util.UUID;

public record PartnerResponse(
        UUID id,
        String name,
        String commercialName,
        String commercialPhoneNumber,
        String commercialEmail,
        String geographicalArea,
        String specialCondition,
        TransportType transportType,
        PartnerStatus status,
        Date createdAt,
        Date lastUpdatedAt
) {
}
