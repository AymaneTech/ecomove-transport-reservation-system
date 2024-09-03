package com.wora.menu.infrastructure.presentation;

import com.wora.common.utils.InputScanner;
import com.wora.contract.infrastructure.presentation.ContractUi;
import com.wora.discount.infrastructure.presentation.DiscountUi;
import com.wora.partner.infrastcutre.presentation.PartnerUi;
import com.wora.ticket.infrastructure.presentation.TicketUi;

public class MainMenu {
    private final PartnerUi partnerUi;
    private final ContractUi contractUi;
    private final DiscountUi discountUi;
    private final TicketUi ticketUi;

    public MainMenu(PartnerUi partnerUi, ContractUi contractUi, DiscountUi discountUi, TicketUi ticketUi) {
        this.partnerUi = partnerUi;
        this.contractUi = contractUi;
        this.discountUi = discountUi;
        this.ticketUi = ticketUi;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\t 1- Manage Partners.");
            System.out.println("\t 2- Manage Contracts.");
            System.out.println("\t 2- Manage Discounts.");
            System.out.println("\t 2- Manage Tickets.");
            System.out.println("\t 0- Exit.");

            final Integer choice = InputScanner.scanInt("Please enter your choice:  ");
            System.out.println("your choice is " + choice);

            switch (choice) {
                case 1 -> partnerUi.showMenu();
                case 2 -> contractUi.showMenu();
                case 3 -> discountUi.showMenu();
                case 4 -> ticketUi.showMenu();
                default -> {
                    System.out.println("invalid choice");
                    showMenu();
                }
            }
        }

    }
}
