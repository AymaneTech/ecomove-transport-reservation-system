package com.wora.client.application.dtos.requests;

import com.wora.client.domain.valueObjects.Name;

public record CreateClientDto(Name name, String email, String phone) {}
