package com.wora.client.application.dtos.responses;

import com.wora.client.domain.valueObjects.Name;

public record ClientResponse(Name name, String email, String phone) {
}
