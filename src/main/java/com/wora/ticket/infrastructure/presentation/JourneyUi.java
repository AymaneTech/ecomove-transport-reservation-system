package com.wora.ticket.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.menu.infrastructure.presentation.MainMenu;
import com.wora.ticket.application.dtos.requests.CreateJourneyDto;
import com.wora.ticket.application.dtos.responses.JourneyResponse;
import com.wora.ticket.application.services.JourneyService;
import com.wora.ticket.domain.valueObjects.JourneyId;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.wora.common.utils.InputScanner.*;

public class JourneyUi {

    private final JourneyService journeyService;
    private MainMenu menu;

    public JourneyUi(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public void showMenu() {
        System.out.println("==== Journey Menu ====");
        System.out.println("1. Create Journey");
        System.out.println("2. List All Journeys");
        System.out.println("3. List Journey by ID");
        System.out.println("0. Back to main menu");

        final Integer userChoice = scanInt("Please enter your choice: ");

        switch (userChoice) {
            case 1 -> this.create();
            case 2 -> this.listAll();
            case 3 -> this.listById();
            case 0 -> menu.showMenu();
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        }
    }

    public void create() {
        System.out.println("\t Please enter the required data to create a new journey!");
        clearBuffer();

        final String startStationId = scanString("Please enter the start station ID: ");
        final String endStationId = scanString("Please enter the end station ID: ");
        final Double distance = scanDouble("Please enter the distance between the stations: ");

        final CreateJourneyDto journeyDto = new CreateJourneyDto(
                new StationId(UUID.fromString(startStationId)),
                new StationId(UUID.fromString(endStationId)),
                distance
        );

        journeyService.create(journeyDto);
        System.out.println("Journey created successfully!");
        this.showMenu();
    }

    public void listAll() {
        final List<JourneyResponse> journeys = journeyService.findAll();
        System.out.println(getTable(journeys));
        this.showMenu();
    }

    public void listById() {
        clearBuffer();
        final String journeyId = scanString("Please enter the ID of the journey you are looking for: ");
        final JourneyResponse journey = journeyService.findById(new JourneyId(UUID.fromString(journeyId)));
        System.out.println(getTable(Collections.singletonList(journey)));
        this.showMenu();
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<JourneyResponse> journeys) {
        return AsciiTable.getTable(journeys, Arrays.asList(
                new Column().header("Start Station").with(journey -> journey.start().name()),
                new Column().header("End Station").with(journey -> journey.end().name()),
                new Column().header("Distance").with(journey -> journey.distance().toString())
        ));
    }
}
