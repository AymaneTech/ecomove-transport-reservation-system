package com.wora.menu.infrastructure.presentation;

import com.wora.common.utils.InputScanner;
import com.wora.contract.infrastructure.presentation.ContractUi;
import com.wora.discount.infrastructure.presentation.DiscountUi;
import com.wora.partner.infrastcutre.presentation.PartnerUi;
import com.wora.ticket.infrastructure.presentation.JourneyUi;
import com.wora.ticket.infrastructure.presentation.StationUi;
import com.wora.ticket.infrastructure.presentation.TicketUi;

import java.awt.*;

import static com.wora.common.utils.InputScanner.clearBuffer;

public class AdministrationMenu {
    private final PartnerUi partnerUi;
    private final ContractUi contractUi;
    private final DiscountUi discountUi;
    private final TicketUi ticketUi;
    private final StationUi stationUi;
    private final JourneyUi journeyUi;
    private ChooseRoleMenu roleMenu;

    public AdministrationMenu(PartnerUi partnerUi, ContractUi contractUi, DiscountUi discountUi, TicketUi ticketUi, StationUi stationUi, JourneyUi journeyUi) {
        this.partnerUi = partnerUi;
        this.contractUi = contractUi;
        this.discountUi = discountUi;
        this.ticketUi = ticketUi;
        this.stationUi = stationUi;
        this.journeyUi = journeyUi;
    }

    public void showMenu() {
        try {
            while (true) {
                System.out.println("\t 1- Manage Partners.");
                System.out.println("\t 2- Manage Contracts.");
                System.out.println("\t 3- Manage Discounts.");
                System.out.println("\t 4- Manage Tickets.");
                System.out.println("\t 5- Manage Stations.");
                System.out.println("\t 6- Manage Journeys.");
                System.out.println("\t 0- Log out.");

                final Integer choice = InputScanner.scanInt("Please enter your choice:  ");

                switch (choice) {
                    case 1 -> partnerUi.showMenu();
                    case 2 -> contractUi.showMenu();
                    case 3 -> discountUi.showMenu();
                    case 4 -> ticketUi.showMenu();
                    case 5 -> stationUi.showMenu();
                    case 6 -> journeyUi.showMenu();
                    case 0 -> roleMenu.showMenu();
                    default -> {
                        System.out.println("invalid choice");
                        showMenu();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            clearBuffer();
            showMenu();
        }

    }

    public void setMenu(ChooseRoleMenu mainMenu) {
        this.roleMenu = mainMenu;
    }
}
