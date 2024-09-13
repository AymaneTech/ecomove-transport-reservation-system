package com.wora.ticket.infrastructure.presentation.client;

import com.wora.ticket.application.services.SearchForTicketService;

import java.time.LocalDate;

import static com.wora.common.utils.InputScanner.scanLocalDate;
import static com.wora.common.utils.InputScanner.scanString;

public class SearchForTicketUi {
    private final SearchForTicketService searchForTicketService;

    public SearchForTicketUi(SearchForTicketService searchForTicketService) {
        this.searchForTicketService = searchForTicketService;
    }

    public void search() {
        System.out.println("Please to enter all the information to do your search");

        final String startStation = scanString("Please to enter the name of city you are in: ");
        final String endStation = scanString("Please to enter the name of city you are out: ");
        final LocalDate startDate = scanLocalDate("Please to enter the date you are in: ");


    }
}
