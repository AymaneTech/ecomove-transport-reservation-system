package com.wora.authentication.infrastructure.presentation;

import com.wora.authentication.application.dtos.requests.LoginClientDto;
import com.wora.authentication.application.dtos.requests.RegisterClientDto;
import com.wora.authentication.application.services.AuthenticationService;
import com.wora.authentication.application.services.impl.SessionManagerImpl;
import com.wora.client.application.dtos.responses.ClientResponse;
import com.wora.menu.infrastructure.presentation.AdministrationMenu;

import static com.wora.common.utils.InputScanner.*;

public class AuthenticationUi {
    private final AuthenticationService service;
    private AdministrationMenu menu;


    public AuthenticationUi(AuthenticationService service) {
        this.service = service;
    }

    public void setMenu(AdministrationMenu menu) {
        this.menu = menu;
    }

    public void showMenu() {
        System.out.println("======= Authentication ======");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Back to main menu");

        final Integer userChoice = scanInt("Please enter your choice: ");
        switch (userChoice) {
            case 1 -> this.login();
            case 2 -> this.register();
            case 0 -> menu.showMenu();
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        }
    }

    public void register() {
        clearBuffer();
        System.out.println("Please to enter the following information to register you!");
        final String firstName = scanString("Please enter your first name: ");
        final String lastName = scanString("Please enter your first name: ");
        final String email = scanString("Please enter your email: ");
        final String phone = scanString("Please enter your phone number: ");

        final RegisterClientDto dto = new RegisterClientDto(firstName, lastName, email, phone);
        ClientResponse registeredClient = service.register(dto);

        isUserAuthenticated(registeredClient);
    }

    public void login() {
        clearBuffer();
        System.out.println("Please to enter you email to login");
        final String email = scanString("Please enter your email: ");

        final LoginClientDto dto = new LoginClientDto(email);
        final ClientResponse client = service.login(dto);
        isUserAuthenticated(client);
    }

    public Boolean isUserAuthenticated(ClientResponse client) {
        if (!SessionManagerImpl.isAuthenticated()) {
            System.out.println("failed to authenticate, Please try again!");
            return false;
        }
        System.out.println("you are logged in: " + client.name());
        System.out.println("Please to use always this email to login : " + client.email());
        return true;

    }
}
