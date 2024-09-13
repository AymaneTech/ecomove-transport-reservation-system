package com.wora.ticket.infrastructure.presentation.administration;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.menu.infrastructure.presentation.AdministrationMenu;
import com.wora.ticket.application.dtos.requests.CreateStationDto;
import com.wora.ticket.application.dtos.requests.UpdateStationDto;
import com.wora.ticket.application.dtos.responses.StationResponse;
import com.wora.ticket.application.services.StationService;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.*;

import static com.wora.common.utils.InputScanner.*;

public class StationUi {

    private final StationService stationService;
    private AdministrationMenu menu;

    public StationUi(StationService stationService) {
        this.stationService = stationService;
    }

    public void showMenu() {
        System.out.println("==== Station Menu ====");
        System.out.println("1. Create Station");
        System.out.println("2. Update Station");
        System.out.println("3. Delete Station");
        System.out.println("4. List All Stations");
        System.out.println("5. List Station by ID");
        System.out.println("0. Back to main menu");

        final Integer userChoice = scanInt("Please enter your choice: ");

        switch (userChoice) {
            case 1 -> this.create();
            case 2 -> this.update();
            case 3 -> this.delete();
            case 4 -> this.listAll();
            case 5 -> this.listById();
            case 0 -> menu.showMenu();
            default -> throw new IllegalStateException("Unexpected value: " + userChoice);
        }
    }

    public void listAll() {
        final List<StationResponse> stations = stationService.findAll();
        System.out.println(getTable(stations));
        this.showMenu();
    }

    public void listById() {
        clearBuffer();
        final String stationId = scanString("Please enter the ID of the station you are looking for: ");
        final StationResponse station = stationService.findById(new StationId(UUID.fromString(stationId)));
        System.out.println(getTable(Collections.singletonList(station)));
        this.showMenu();
    }

    public void create() {
        System.out.println("\t Please enter the required data to create a new station!");
        clearBuffer();

        final String name = scanString("Please enter the name of the station: ");
        final String city = scanString("Please enter the city of the station: ");

        final CreateStationDto stationDto = new CreateStationDto(name, city);
        stationService.create(stationDto);

        System.out.println("Station created successfully!");
        this.showMenu();
    }

    public void update() {
        System.out.println("\t Please enter the required data to update an existing station!");

        clearBuffer();
        final String id = scanString("Please enter the ID of the station to update: ");
        final StationId stationId = new StationId(UUID.fromString(id));

        try {
            final StationResponse existingStation = stationService.findById(stationId);

            final String newName = scanString("Enter new name (current: " + existingStation.name() + "): ");
            final String newCity = scanString("Enter new city (current: " + existingStation.city() + "): ");

            final UpdateStationDto stationDto = new UpdateStationDto(newName.isEmpty() ? existingStation.name() : newName,
                    newCity.isEmpty() ? existingStation.city() : newCity);

            stationService.update(stationId, stationDto);
            System.out.println("Station updated successfully!");
        } catch (Exception e) {
            System.out.println("Station not found with id " + stationId.value());
        }

        this.showMenu();
    }

    public void delete() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the station to delete: ");
        final StationId stationId = new StationId(UUID.fromString(id));

        if (!stationService.existsById(stationId)) {
            System.out.println("The station does not exist!");
            return;
        }

        stationService.delete(stationId);
        System.out.println("Station deleted successfully!");
        this.showMenu();
    }

    public void setMenu(AdministrationMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<StationResponse> stations) {
        return AsciiTable.getTable(stations, Arrays.asList(
                new Column().header("Station ID").with(station -> station.id().value().toString()),
                new Column().header("Name").with(StationResponse::name),
                new Column().header("City").with(StationResponse::city)
        ));
    }
}
