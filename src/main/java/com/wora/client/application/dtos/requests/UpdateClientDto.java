package com.wora.client.application.dtos.requests;

import com.wora.client.domain.valueObjects.Name;

public record UpdateClientDto(Name name, String email, String phone) {}
