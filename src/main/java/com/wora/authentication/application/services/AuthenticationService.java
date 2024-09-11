package com.wora.authentication.application.services;

import com.wora.authentication.application.dtos.requests.LoginClientDto;
import com.wora.authentication.application.dtos.requests.RegisterClientDto;
import com.wora.client.application.dtos.responses.ClientResponse;

public interface AuthenticationService {
    ClientResponse register(RegisterClientDto dto);

    ClientResponse login(LoginClientDto dto);
}
