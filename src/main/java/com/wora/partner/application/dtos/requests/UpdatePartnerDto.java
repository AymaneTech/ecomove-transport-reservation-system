package com.wora.partner.application.dtos.requests;

import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;
import com.wora.partner.domain.valueObjects.CommercialInfo;

public record UpdatePartnerDto(
        String name,
        CommercialInfo commercialInfo,
        String geographicalArea,
        String specialCondition,
        TransportType transportType,
        PartnerStatus status
) {
}
