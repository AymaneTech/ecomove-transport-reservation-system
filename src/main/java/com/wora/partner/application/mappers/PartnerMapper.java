package com.wora.partner.application.mappers;

import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.dtos.requests.UpdatePartnerDto;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.domain.entities.Partner;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.UUID;

public class PartnerMapper {
    public Partner map(CreatePartnerDto dto) {
        return new Partner(
                new PartnerId(UUID.randomUUID()),
                dto.name(),
                dto.commercialInfo(),
                dto.geographicalArea(),
                dto.specialCondition(),
                dto.transportType(),
                dto.status()
        );
    }

    public Partner map(UpdatePartnerDto dto, UUID id) {
        return new Partner(
                new PartnerId(id),
                dto.name(),
                dto.commercialInfo(),
                dto.geographicalArea(),
                dto.specialCondition(),
                dto.transportType(),
                dto.status()
        );
    }

    public PartnerResponse map(Partner partner) {
        return new PartnerResponse(
                partner.getId().value(),
                partner.getName(),
                partner.getCommercialInfo().commercialName(),
                partner.getCommercialInfo().commercialPhoneNumber(),
                partner.getCommercialInfo().commercialEmail(),
                partner.getGeographicArea(),
                partner.getSpecialCondition(),
                partner.getTransportType(),
                partner.getStatus(),
                partner.getCreatedAt(),
                partner.getUpdatedAt()
        );
    }
}
