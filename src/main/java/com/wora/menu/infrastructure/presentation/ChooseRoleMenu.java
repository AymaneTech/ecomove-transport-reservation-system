package com.wora.menu.infrastructure.presentation;

import com.wora.authentication.infrastructure.presentation.AuthenticationUi;

import static com.wora.common.utils.InputScanner.scanInt;

public class ChooseRoleMenu {
    private final AdministrationMenu administrationMenu;
    private final AuthenticationUi authenticationUi;

    public ChooseRoleMenu(AdministrationMenu administrationMenu, AuthenticationUi authenticationUi) {
        this.administrationMenu = administrationMenu;
        this.authenticationUi = authenticationUi;
    }

    public void showMenu() {
        System.out.println("Please to choose your role to continue. ");
        System.out.println("1. Admin");
        System.out.println("2. Client");
        System.out.println("0. Exit");

        int userChoice = scanInt("Please to enter the number of your choice:  ");
        switch (userChoice) {
            case 1 -> administrationMenu.showMenu();
            case 2 -> authenticationUi.showMenu();
            case 0 -> System.exit(0);
        }
    }
}
