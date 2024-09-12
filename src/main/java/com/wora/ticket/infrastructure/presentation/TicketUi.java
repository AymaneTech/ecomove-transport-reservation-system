package com.wora.ticket.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.menu.infrastructure.presentation.AdministrationMenu;
import com.wora.partner.domain.enums.TransportType;
import com.wora.ticket.application.dtos.requests.CreateTicketDto;
import com.wora.ticket.application.dtos.requests.JourneyDto;
import com.wora.ticket.application.dtos.requests.UpdateTicketDto;
import com.wora.ticket.application.dtos.responses.TicketResponse;
import com.wora.ticket.application.services.TicketService;
import com.wora.ticket.domain.enums.TicketStatus;
import com.wora.ticket.domain.exceptions.TicketNotFoundException;
import com.wora.ticket.domain.valueObjects.Price;
import com.wora.ticket.domain.valueObjects.TicketId;

import java.time.LocalDateTime;
import java.util.*;

import static com.wora.common.utils.InputScanner.*;

public class TicketUi {

    private final TicketService ticketService;
    private final ContractService contractService;
    private AdministrationMenu menu;

    public TicketUi(TicketService ticketService, ContractService contractService) {
        this.ticketService = ticketService;
        this.contractService = contractService;
    }

    public void showMenu() {
        System.out.println("==== Ticket Menu ====");
        System.out.println("1. Create Ticket");
        System.out.println("2. Update Ticket");
        System.out.println("3. Delete Ticket");
        System.out.println("4. Change Ticket Status");
        System.out.println("5. List Tickets");
        System.out.println("6. List Ticket by ID");
        System.out.println("0. Back to main menu");

        final Integer userChoice = scanInt("Please enter your choice: ");

        switch (userChoice) {
            case 1 -> this.create();
            case 2 -> this.update();
            case 3 -> this.delete();
            case 4 -> this.changeStatus();
            case 5 -> this.listAll();
            case 6 -> this.listById();
            case 0 -> menu.showMenu();
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        }
    }

    public void listAll() {
        final List<TicketResponse> tickets = ticketService.findAll();
        System.out.println(getTable(tickets));
        this.showMenu();
    }

    public void listById() {
        clearBuffer();
        final String ticketId = scanString("Please enter the ID of the ticket you are looking for: ");
        final TicketResponse ticket = ticketService.findById(new TicketId(UUID.fromString(ticketId)));
        System.out.println(getTable(Collections.singletonList(ticket)));
        this.showMenu();
    }

    public void create() {
        System.out.println("\t Please enter the required data to create a new ticket!");
        clearBuffer();

        Optional<ContractId> contractId = Optional.empty();
        while (contractId.isEmpty()) {
            final String id = scanString("Please enter the ID of the associated contract: ");
            contractId = parseAndValidateContractId(id);
            if (contractId.isEmpty()) {
                System.out.println("Invalid contract ID. Please try again.");
            }
        }

        final Price sellingPrice = scanPrice("Please enter the selling price: ");
        final Price purchasePrice = scanPrice("Please enter the purchase price: ");

        final List<TransportType> transportTypes = Arrays.asList(TransportType.values());
        for (int i = 0; i < transportTypes.size(); i++) {
            System.out.println(i + "- " + transportTypes.get(i));
        }
        final Integer transportTypeId = scanInt("Please enter the number of the transport type: ");
        final TransportType transportType = transportTypes.get(transportTypeId);

        final List<TicketStatus> ticketStatuses = Arrays.asList(TicketStatus.values());
        for (int i = 0; i < ticketStatuses.size(); i++) {
            System.out.println(i + "- " + ticketStatuses.get(i));
        }
        final Integer statusId = scanInt("Please enter the number of the ticket status: ");
        final TicketStatus status = ticketStatuses.get(statusId);

        clearBuffer();
        final String startStationName = scanString("Please enter the name of the start station: ");
        final String endStationName = scanString("Please enter the name of the end station: ");

        final LocalDateTime startDate = scanLocalDateTime("Please enter the date and time of journey start: ");
        final LocalDateTime endDate = scanLocalDateTime("Please enter the date and time of journey end: ");


        final CreateTicketDto ticketDto = new CreateTicketDto(
                contractId.get(),
                sellingPrice, purchasePrice,
                new JourneyDto(startStationName, endStationName),
                startDate, endDate,
                transportType, status
        );

        ticketService.create(ticketDto);
        System.out.println("Ticket created successfully!");
        this.showMenu();
    }

    public void update() {
        System.out.println("\t Please enter the required data to update an existing ticket!");

        clearBuffer();
        final String id = scanString("Please enter the ID of the ticket to update: ");
        final TicketId ticketId = new TicketId(UUID.fromString(id));

        try {
            final TicketResponse existingTicket = ticketService.findById(ticketId);

            final Price sellingPrice = scanPrice("Enter new selling price (current: " + existingTicket.sellingPrice() + "): ");
            final Price purchasePrice = scanPrice("Enter new purchase price (current: " + existingTicket.purchasePrice() + "): ");

            System.out.println("Current transport type: " + existingTicket.transportType());
            final List<TransportType> transportTypes = Arrays.asList(TransportType.values());
            for (int i = 0; i < transportTypes.size(); i++) {
                System.out.println(i + "- " + transportTypes.get(i));
            }
            final int transportTypeId = scanInt("Enter the number of the new transport type (or -1 to keep current): ");
            final TransportType updatedTransportType = transportTypeId >= 0 ? transportTypes.get(transportTypeId) : existingTicket.transportType();

            System.out.println("Current ticket status: " + existingTicket.status());
            final List<TicketStatus> ticketStatuses = Arrays.asList(TicketStatus.values());
            for (int i = 0; i < ticketStatuses.size(); i++) {
                System.out.println(i + "- " + ticketStatuses.get(i));
            }
            final int statusId = scanInt("Enter the number of the new ticket status (or -1 to keep current): ");
            final TicketStatus updatedStatus = statusId >= 0 ? ticketStatuses.get(statusId) : existingTicket.status();

            clearBuffer();
            final String contractId = scanString("Enter new contract ID (current: " + existingTicket.contract().id().toString() + "): ");
            final ContractId updatedContractId = contractId.isEmpty() ? existingTicket.contract().id() : new ContractId(UUID.fromString(contractId));

            final LocalDateTime startDate = scanLocalDateTime("Please enter the date and time of journey start: ");
            final LocalDateTime endDate = scanLocalDateTime("Please enter the date and time of journey end: ");

            final String startStation = scanString("Enter the name of city you are in");
            final String endStation = scanString("Enter the name of city you want to go to");

            final UpdateTicketDto ticketDto = new UpdateTicketDto(
                    updatedContractId,
                    sellingPrice, purchasePrice,
                    new JourneyDto(startStation, endStation),
                    startDate, endDate,
                    updatedTransportType, updatedStatus
            );

            ticketService.update(ticketId, ticketDto);
            System.out.println("Ticket updated successfully!");
        } catch (TicketNotFoundException e) {
            System.out.println("Ticket not found with id " + ticketId.value());
        }

        this.showMenu();
    }

    public void delete() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the ticket to delete: ");
        final TicketId ticketId = new TicketId(UUID.fromString(id));

        if (!ticketService.existsById(ticketId)) {
            System.out.println("The ticket does not exist!");
            return;
        }

        ticketService.delete(ticketId);
        System.out.println("Ticket deleted successfully!");
        this.showMenu();
    }

    public void changeStatus() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the ticket to change its status: ");
        final TicketId ticketId = new TicketId(UUID.fromString(id));

        if (!ticketService.existsById(ticketId)) {
            System.out.println("The ticket does not exist!");
            return;
        }

        final List<TicketStatus> ticketStatuses = Arrays.asList(TicketStatus.values());
        for (int i = 0; i < ticketStatuses.size(); i++) {
            System.out.println(i + "- " + ticketStatuses.get(i));
        }
        final int statusId = scanInt("Enter the number of the new ticket status: ");
        ticketService.changeStatus(ticketId, ticketStatuses.get(statusId));
        System.out.println("Ticket status changed successfully!");
        this.showMenu();
    }

    public void setMenu(AdministrationMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<TicketResponse> tickets) {
        return AsciiTable.getTable(tickets, Arrays.asList(
                new Column().with(ticketResponse -> ticketResponse.id().value().toString()),
                new Column().header("Contract ID").with(ticketResponse -> ticketResponse.contract().id().value().toString()),
                new Column().header("Selling Price").with(ticketResponse -> ticketResponse.sellingPrice().toString()),
                new Column().header("Purchase Price").with(ticketResponse -> ticketResponse.purchasePrice().toString()),
                new Column().header("Selling Date").with(ticketResponse -> ticketResponse.sellingDate().toString()),
                new Column().header("Transport Type").with(ticketResponse -> ticketResponse.transportType().toString()),
                new Column().header("Status").with(ticketResponse -> ticketResponse.status().toString()),
                new Column().header("Created At").with(ticketResponse -> ticketResponse.createdAt() != null ? ticketResponse.createdAt().toString() : "not saved"),
                new Column().header("Updated At").with(ticketResponse -> ticketResponse.updatedAt() != null ? ticketResponse.updatedAt().toString() : "Not Updated Yet")
        ));
    }

    private Optional<ContractId> parseAndValidateContractId(String id) {
        final ContractId contractId = new ContractId(UUID.fromString(id));
        if (!contractService.existsById(contractId)) return Optional.empty();

        return Optional.of(contractId);
    }
}