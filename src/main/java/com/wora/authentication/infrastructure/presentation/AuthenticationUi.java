package com.wora.authentication.infrastructure.presentation;

import com.wora.authentication.application.services.AuthenticationService;

public class AuthenticationUi {
    private final AuthenticationService authenticationService;


    public AuthenticationUi(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    
}
