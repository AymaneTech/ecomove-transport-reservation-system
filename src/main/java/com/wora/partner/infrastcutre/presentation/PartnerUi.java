package com.wora.partner.infrastcutre.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.menu.infrastructure.presentation.MainMenu;
import com.wora.partner.application.dtos.requests.CreatePartnerDto;
import com.wora.partner.application.dtos.requests.UpdatePartnerDto;
import com.wora.partner.application.dtos.responses.PartnerResponse;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.domain.enums.PartnerStatus;
import com.wora.partner.domain.enums.TransportType;
import com.wora.partner.domain.exceptions.PartnerNotFoundException;
import com.wora.partner.domain.valueObjects.CommercialInfo;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.wora.common.utils.InputScanner.*;

public class PartnerUi {

    private final PartnerService partnerService;
    private MainMenu menu;

    public PartnerUi(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    public void showMenu() {
        System.out.println("==== Partner Menu ====");
        System.out.println("1. Create Partner.");
        System.out.println("2. Update Partner.");
        System.out.println("3. Delete Partner.");
        System.out.println("4. Change Partner Status");
        System.out.println("5. List Partners.");
        System.out.println("6. List partner by id.");
        System.out.println("0- Back to main menu.");

        final Integer userChoice = scanInt("Please enter your choice:  ");
        System.out.println("your choice is " + userChoice);

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
        final List<PartnerResponse> partners = partnerService.findAll();
        System.out.println(getTable(partners));
        this.showMenu();
    }

    public void listById() {
        final String partnerId = scanString("Please enter the ID of partner you are looking for: ");
        final PartnerResponse partner;
        try {
            partner = partnerService.findById(new PartnerId(UUID.fromString(partnerId)));
            System.out.println(getTable(Collections.singletonList(partner)));
        } catch (PartnerNotFoundException e) {
            System.out.println("no partner with this id");
        }
        this.showMenu();
    }

    public void create() {
        System.out.println("\t Please to enter the required data to create new partner!");
        final List<TransportType> transportTypes = Arrays.asList(TransportType.values());
        final List<PartnerStatus> partnerStatuses = Arrays.asList(PartnerStatus.values());
        final String name, commercialName, commercialEmail, commercialPhone, geographicalArea, specialCondition;
        final PartnerStatus partnerStatus;
        final TransportType transportType;

        name = scanString("Please enter the name of the partner: ");
        commercialName = scanString("Please enter the commercial name of the partner: ");
        commercialEmail = scanString("Please enter the commercial email of the partner: ");
        commercialPhone = scanString("Please enter the commercial phone of the partner: ");
        geographicalArea = scanString("Please enter the geographical area: ");
        specialCondition = scanString("Please enter the special condition: ");

        for (int i = 0; i < transportTypes.size(); i++) {
            System.out.println(i + "- " + transportTypes.get(i));
        }
        final Integer typeId = scanInt("Please enter the number of type of the transport: ");
        transportType = transportTypes.get(typeId);

        for (int i = 0; i < partnerStatuses.size(); i++) {
            System.out.println(i + "- " + partnerStatuses.get(i));
        }
        final Integer statusId = scanInt("Please enter the number of status of the partner: ");
        partnerStatus = partnerStatuses.get(statusId);

        final CreatePartnerDto partnerDto = new CreatePartnerDto(
                name,
                new CommercialInfo(commercialName, commercialPhone, commercialEmail),
                geographicalArea,
                specialCondition,
                transportType,
                partnerStatus
        );

        partnerService.create(partnerDto);
        System.out.println("Partner created successfully!");
        this.showMenu();

    }

    public void update() {
        System.out.println("\t Please enter the required data to update an existing partner!");

        clearBuffer();
        final String id = scanString("Please enter the ID of the partner to update: ");
        final PartnerId partnerId = new PartnerId(UUID.fromString(id));

        final PartnerResponse existingPartner;
        try {
            existingPartner = partnerService.findById(partnerId);

            final List<TransportType> transportTypes = Arrays.asList(TransportType.values());
            final List<PartnerStatus> partnerStatuses = Arrays.asList(PartnerStatus.values());

            final String name = scanString("Enter new name (press Enter to keep current: " + existingPartner.name() + "): ");
            final String updatedName = name.isEmpty() ? existingPartner.name() : name;

            final String commercialName = scanString("Enter new commercial name (current: " + existingPartner.commercialName() + "): ");
            final String updatedCommercialName = commercialName.isEmpty() ? existingPartner.commercialName() : commercialName;

            final String commercialEmail = scanString("Enter new commercial email (current: " + existingPartner.commercialEmail() + "): ");
            final String updatedCommercialEmail = commercialEmail.isEmpty() ? existingPartner.commercialEmail() : commercialEmail;

            final String commercialPhone = scanString("Enter new commercial phone (current: " + existingPartner.commercialPhoneNumber() + "): ");
            final String updatedCommercialPhone = commercialPhone.isEmpty() ? existingPartner.commercialPhoneNumber() : commercialPhone;

            final String geographicalArea = scanString("Enter new geographical area (current: " + existingPartner.geographicalArea() + "): ");
            final String updatedGeographicalArea = geographicalArea.isEmpty() ? existingPartner.geographicalArea() : geographicalArea;

            final String specialCondition = scanString("Enter new special condition (current: " + existingPartner.specialCondition() + "): ");
            final String updatedSpecialCondition = specialCondition.isEmpty() ? existingPartner.specialCondition() : specialCondition;

            System.out.println("Current transport type: " + existingPartner.transportType());
            for (int i = 0; i < transportTypes.size(); i++) {
                System.out.println(i + "- " + transportTypes.get(i));
            }
            final int typeId = scanInt("Enter the number of the new transport type (or -1 to keep current): ");
            final TransportType updatedTransportType = typeId >= 0 ? transportTypes.get(typeId) : existingPartner.transportType();

            System.out.println("Current partner status: " + existingPartner.status());
            for (int i = 0; i < partnerStatuses.size(); i++) {
                System.out.println(i + "- " + partnerStatuses.get(i));
            }
            final int statusId = scanInt("Enter the number of the new partner status (or -1 to keep current): ");
            final PartnerStatus updatedPartnerStatus = statusId >= 0 ? partnerStatuses.get(statusId) : existingPartner.status();

            final UpdatePartnerDto partnerDto = new UpdatePartnerDto(
                    updatedName,
                    new CommercialInfo(updatedCommercialName, updatedCommercialPhone, updatedCommercialEmail),
                    updatedGeographicalArea,
                    updatedSpecialCondition,
                    updatedTransportType,
                    updatedPartnerStatus
            );

            partnerService.update(partnerId, partnerDto);
            System.out.println("Partner updated successfully!");
        } catch (PartnerNotFoundException e) {
            System.out.println("partner with id " + id + " not found");
        }
        this.showMenu();
    }

    public void delete() {
        clearBuffer();
        final String id = scanString("Please enter the ID of partner to delete: ");
        final PartnerId partnerId = new PartnerId(UUID.fromString(id));

        if (!partnerService.existsById(partnerId)) {
            System.out.println("the partner does not exist!");
            return;
        }

        partnerService.delete(partnerId);
        System.out.println("Partner deleted successfully!");
        this.showMenu();
    }

    public void changeStatus() {
        final String id = scanString("Please enter the ID of partner to change his status: ");
        final PartnerId partnerId = new PartnerId(UUID.fromString(id));

        if (!partnerService.existsById(partnerId)) {
            System.out.println("the partner does not exist!");
            return;
        }

        final List<PartnerStatus> partnerStatuses = Arrays.asList(PartnerStatus.values());
        for (int i = 0; i < partnerStatuses.size(); i++) {
            System.out.println(i + "- " + partnerStatuses.get(i));
        }
        final int statusId = scanInt("Enter the number of the new partner status: ");
        partnerService.changeStatus(partnerId, partnerStatuses.get(statusId));
        System.out.println("partner status changed successfully!");
        this.showMenu();
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<PartnerResponse> partners) {
        return AsciiTable.getTable(partners, Arrays.asList(
                new Column().with(partnerResponse -> partnerResponse.id().toString()),
                new Column().header("Name").with(PartnerResponse::name),
                new Column().header("Commercial Name").with(PartnerResponse::commercialName),
                new Column().header("Commercial Phone Number").with(PartnerResponse::commercialPhoneNumber),
                new Column().header("Commercial Email").with(PartnerResponse::commercialEmail),
                new Column().header("Geographical Area").with(PartnerResponse::geographicalArea),
                new Column().header("Special Condition").with(PartnerResponse::specialCondition),
                new Column().header("Transport Type").with(partnerResponse -> partnerResponse.transportType().toString()),
                new Column().header("Status").with(partnerResponse -> partnerResponse.status().toString()),
                new Column().header("Created At").with(partnerResponse -> partnerResponse.createdAt().toString()),
                new Column().header("Last Updated At").with(partnerResponse -> partnerResponse.lastUpdatedAt() != null ? partnerResponse.lastUpdatedAt().toString() : "Not Updated Yet")
        ));
    }

}
