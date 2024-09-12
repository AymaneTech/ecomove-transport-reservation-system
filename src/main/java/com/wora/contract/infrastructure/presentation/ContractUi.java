package com.wora.contract.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.contract.application.dtos.requests.CreateContractDto;
import com.wora.contract.application.dtos.requests.UpdateContractDto;
import com.wora.contract.application.dtos.responses.ContractResponse;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.domain.enums.ContractStatus;
import com.wora.contract.domain.exceptions.ContractNotFoundException;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.menu.infrastructure.presentation.AdministrationMenu;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.domain.valueObjects.PartnerId;

import java.util.*;

import static com.wora.common.utils.InputScanner.*;

public class ContractUi {

    private final ContractService contractService;
    private final PartnerService partnerService;
    private AdministrationMenu menu;

    public ContractUi(ContractService contractService, PartnerService partnerService) {
        this.contractService = contractService;
        this.partnerService = partnerService;
    }

    public void showMenu() {
        System.out.println("==== Contract Menu ====");
        System.out.println("1. Create Contract");
        System.out.println("2. Update Contract");
        System.out.println("3. Delete Contract");
        System.out.println("4. Change Contract Status");
        System.out.println("5. List Contracts");
        System.out.println("6. List Contract by ID");
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
        final List<ContractResponse> contracts = contractService.findAll();
        System.out.println(getTable(contracts));
        this.showMenu();
    }

    public void listById() {
        clearBuffer();
        final String contractId = scanString("Please enter the ID of the contract you are looking for: ");
        final ContractResponse contract = contractService.findById(new ContractId(UUID.fromString(contractId)));
        System.out.println(getTable(Collections.singletonList(contract)));
        this.showMenu();
    }

    public void create() {
        System.out.println("\t Please enter the required data to create a new contract!");
        clearBuffer();

        final String specialPrice = scanString("Please enter the special price: ");
        final String agreementCondition = scanString("Please enter the agreement condition: ");
        final Boolean renewable = scanBoolean("Is the contract renewable? (true/false): ");
        final Date startedAt = scanDate("Please enter the start date (yyyy-MM-dd): ");
        final Date endsAt = scanDate("Please enter the end date (yyyy-MM-dd): ");

        final List<ContractStatus> contractStatuses = Arrays.asList(ContractStatus.values());
        for (int i = 0; i < contractStatuses.size(); i++) {
            System.out.println(i + "- " + contractStatuses.get(i));
        }
        final Integer statusId = scanInt("Please enter the number of the contract status: ");
        final ContractStatus status = contractStatuses.get(statusId);

        clearBuffer();

        Optional<PartnerId> partnerId = Optional.empty();
        while (partnerId.isEmpty()) {
            final String id = scanString("Please enter the ID of the associated partner: ");
            partnerId = parseAndValidatePartnerId(id);
            if (partnerId.isEmpty()) {
                System.out.println("Invalid partner ID. Please try again.");
            }
        }


        final CreateContractDto contractDto = new CreateContractDto(
                specialPrice,
                agreementCondition,
                renewable,
                startedAt,
                endsAt,
                status,
                partnerId.get()
        );

        contractService.create(contractDto);
        System.out.println("Contract created successfully!");
        this.showMenu();
    }

    public void update() {
        System.out.println("\t Please enter the required data to update an existing contract!");

        clearBuffer();
        final String id = scanString("Please enter the ID of the contract to update: ");
        final ContractId contractId = new ContractId(UUID.fromString(id));

        try {
            final ContractResponse existingContract = contractService.findById(contractId);
            final String specialPrice = scanString("Enter new special price (current: " + existingContract.specialPrice() + "): ");
            final String updatedSpecialPrice = specialPrice.isEmpty() ? existingContract.specialPrice() : specialPrice;

            final String agreementCondition = scanString("Enter new agreement condition (current: " + existingContract.agreementCondition() + "): ");
            final String updatedAgreementCondition = agreementCondition.isEmpty() ? existingContract.agreementCondition() : agreementCondition;

            final Boolean renewable = scanBoolean("Is the contract renewable? (current: " + existingContract.renewable() + "): ");

            final Date startedAt = scanDate("Enter new start date (current: " + existingContract.startedAt() + "): ");
            final Date updatedStartedAt = startedAt == null ? existingContract.startedAt() : startedAt;

            final Date endsAt = scanDate("Enter new end date (current: " + existingContract.endsAt() + "): ");
            final Date updatedEndsAt = endsAt == null ? existingContract.endsAt() : endsAt;

            System.out.println("Current contract status: " + existingContract.status());
            final List<ContractStatus> contractStatuses = Arrays.asList(ContractStatus.values());
            for (int i = 0; i < contractStatuses.size(); i++) {
                System.out.println(i + "- " + contractStatuses.get(i));
            }
            final int statusId = scanInt("Enter the number of the new contract status (or -1 to keep current): ");
            final ContractStatus updatedStatus = statusId >= 0 ? contractStatuses.get(statusId) : existingContract.status();

            clearBuffer();
            final String partnerId = scanString("Enter new partner ID (current: " + existingContract.partner().id() + "): ");
            final PartnerId updatedPartnerId = partnerId.isEmpty() ? new PartnerId(existingContract.partner().id()) : new PartnerId(UUID.fromString(partnerId));

            final UpdateContractDto contractDto = new UpdateContractDto(
                    updatedSpecialPrice,
                    updatedAgreementCondition,
                    renewable,
                    updatedStartedAt,
                    updatedEndsAt,
                    updatedStatus,
                    updatedPartnerId
            );

            contractService.update(contractId, contractDto);
            System.out.println("Contract updated successfully!");
        } catch (ContractNotFoundException e) {
            System.out.println("contract not found with id " + contractId.value());
        }

        this.showMenu();
    }

    public void delete() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the contract to delete: ");
        final ContractId contractId = new ContractId(UUID.fromString(id));

        if (!contractService.existsById(contractId)) {
            System.out.println("The contract does not exist!");
            return;
        }

        contractService.delete(contractId);
        System.out.println("Contract deleted successfully!");
        this.showMenu();
    }

    public void changeStatus() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the contract to change its status: ");
        final ContractId contractId = new ContractId(UUID.fromString(id));

        if (!contractService.existsById(contractId)) {
            System.out.println("The contract does not exist!");
            return;
        }

        final List<ContractStatus> contractStatuses = Arrays.asList(ContractStatus.values());
        for (int i = 0; i < contractStatuses.size(); i++) {
            System.out.println(i + "- " + contractStatuses.get(i));
        }
        final int statusId = scanInt("Enter the number of the new contract status: ");
        contractService.changeStatus(contractId, contractStatuses.get(statusId));
        System.out.println("Contract status changed successfully!");
        this.showMenu();
    }

    public void setMenu(AdministrationMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<ContractResponse> contracts) {
        return AsciiTable.getTable(contracts, Arrays.asList(
                new Column().with(contractResponse -> contractResponse.id().toString()),
                new Column().header("Special Price").with(ContractResponse::specialPrice),
                new Column().header("Agreement Condition").with(ContractResponse::agreementCondition),
                new Column().header("Renewable").with(contractResponse -> contractResponse.renewable().toString()),
                new Column().header("Started At").with(contractResponse -> contractResponse.startedAt().toString()),
                new Column().header("Ends At").with(contractResponse -> contractResponse.endsAt().toString()),
                new Column().header("Status").with(contractResponse -> contractResponse.status().toString()),
                new Column().header("Partner Name").with(contractResponse -> contractResponse.partner() != null ? contractResponse.partner().name() : "--"),
                new Column().header("created at").with(contractResponse -> contractResponse.createdAt().toString()),
                new Column().header("updated at").with(contractResponse -> contractResponse.updatedAt() != null ? contractResponse.updatedAt().toString() : "Not Updated Yet")
        ));
    }

    private Optional<PartnerId> parseAndValidatePartnerId(String id) {
        final PartnerId partnerId = new PartnerId(UUID.fromString(id));
        if (!partnerService.existsById(partnerId)) return Optional.empty();

        return Optional.of(partnerId);
    }
}