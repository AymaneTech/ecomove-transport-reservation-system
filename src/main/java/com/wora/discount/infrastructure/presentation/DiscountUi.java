package com.wora.discount.infrastructure.presentation;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.domain.valueObjects.ContractId;
import com.wora.discount.application.dtos.requests.CreateDiscountDto;
import com.wora.discount.application.dtos.requests.UpdateDiscountDto;
import com.wora.discount.application.dtos.responses.DiscountResponse;
import com.wora.discount.application.services.DiscountService;
import com.wora.discount.domain.enums.DiscountStatus;
import com.wora.discount.domain.enums.ReductionType;
import com.wora.discount.domain.exceptions.DiscountNotFoundException;
import com.wora.discount.domain.valueObjects.DiscountId;
import com.wora.discount.domain.valueObjects.Reduction;
import com.wora.menu.infrastructure.presentation.MainMenu;

import java.util.*;

import static com.wora.common.utils.InputScanner.*;

public class DiscountUi {

    private final DiscountService discountService;
    private final ContractService contractService;
    private MainMenu menu;

    public DiscountUi(DiscountService discountService, ContractService contractService) {
        this.discountService = discountService;
        this.contractService = contractService;
    }

    public void showMenu() {
        System.out.println("==== Discount Menu ====");
        System.out.println("1. Create Discount");
        System.out.println("2. Update Discount");
        System.out.println("3. Delete Discount");
        System.out.println("4. Change Discount Status");
        System.out.println("5. List Discounts");
        System.out.println("6. List Discount by ID");
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
        final List<DiscountResponse> discounts = discountService.findAll();
        System.out.println(getTable(discounts));
        this.showMenu();
    }

    public void listById() {
        clearBuffer();
        final String discountId = scanString("Please enter the ID of the discount you are looking for: ");
        final DiscountResponse discount = discountService.findById(new DiscountId(UUID.fromString(discountId)));
        System.out.println(getTable(Collections.singletonList(discount)));
        this.showMenu();
    }

    public void create() {
        System.out.println("\t Please enter the required data to create a new discount!");
        clearBuffer();

        Optional<ContractId> contractId = Optional.empty();
        while (contractId.isEmpty()) {
            final String id = scanString("Please enter the ID of the associated contract: ");
            contractId = parseAndValidateContractId(id);
            if (contractId.isEmpty()) {
                System.out.println("Invalid contract ID. Please try again.");
            }
        }

        final String name = scanString("Please enter the discount name: ");
        final String description = scanString("Please enter the discount description: ");
        final String conditions = scanString("Please enter the discount conditions: ");
        final Reduction reduction = scanReduction("Please enter the reduction (e.g., 10% or 50EUR): ");
        final Date startedAt = scanDate("Please enter the start date (yyyy-MM-dd): ");
        final Date endsAt = scanDate("Please enter the end date (yyyy-MM-dd): ");

        final List<DiscountStatus> discountStatuses = Arrays.asList(DiscountStatus.values());
        for (int i = 0; i < discountStatuses.size(); i++) {
            System.out.println(i + "- " + discountStatuses.get(i));
        }
        final Integer statusId = scanInt("Please enter the number of the discount status: ");
        final DiscountStatus status = discountStatuses.get(statusId);

        final CreateDiscountDto discountDto = new CreateDiscountDto(
                contractId.get(),
                name,
                description,
                conditions,
                reduction,
                startedAt,
                endsAt,
                status
        );

        discountService.create(discountDto);
        System.out.println("Discount created successfully!");
        this.showMenu();
    }

    public void update() {
        System.out.println("\t Please enter the required data to update an existing discount!");

        clearBuffer();
        final String id = scanString("Please enter the ID of the discount to update: ");
        final DiscountId discountId = new DiscountId(UUID.fromString(id));

        try {
            final DiscountResponse existingDiscount = discountService.findById(discountId);

            final String name = scanString("Enter new name (current: " + existingDiscount.name() + "): ");
            final String updatedName = name.isEmpty() ? existingDiscount.name() : name;

            final String description = scanString("Enter new description (current: " + existingDiscount.description() + "): ");
            final String updatedDescription = description.isEmpty() ? existingDiscount.description() : description;

            final String conditions = scanString("Enter new conditions (current: " + existingDiscount.conditions() + "): ");
            final String updatedConditions = conditions.isEmpty() ? existingDiscount.conditions() : conditions;

            final Reduction reduction = scanReduction("Enter new reduction (current: " + existingDiscount.reduction() + "): ");
            final Reduction updatedReduction = reduction == null ? existingDiscount.reduction() : reduction;

            final Date startedAt = scanDate("Enter new start date (current: " + existingDiscount.startedAt() + "): ");
            final Date updatedStartedAt = startedAt == null ? existingDiscount.startedAt() : startedAt;

            final Date endsAt = scanDate("Enter new end date (current: " + existingDiscount.endsAt() + "): ");
            final Date updatedEndsAt = endsAt == null ? existingDiscount.endsAt() : endsAt;

            System.out.println("Current discount status: " + existingDiscount.status());
            final List<DiscountStatus> discountStatuses = Arrays.asList(DiscountStatus.values());
            for (int i = 0; i < discountStatuses.size(); i++) {
                System.out.println(i + "- " + discountStatuses.get(i));
            }
            final int statusId = scanInt("Enter the number of the new discount status (or -1 to keep current): ");
            final DiscountStatus updatedStatus = statusId >= 0 ? discountStatuses.get(statusId) : existingDiscount.status();

            clearBuffer();
            final String contractId = scanString("Enter new contract ID (current: " + existingDiscount.contract().id().toString() + "): ");
            final ContractId updatedContractId = contractId.isEmpty() ? existingDiscount.contract().id() : new ContractId(UUID.fromString(contractId));

            final UpdateDiscountDto discountDto = new UpdateDiscountDto(
                    updatedContractId,
                    updatedName,
                    updatedDescription,
                    updatedConditions,
                    updatedReduction,
                    updatedStartedAt,
                    updatedEndsAt,
                    updatedStatus
            );

            discountService.update(discountId, discountDto);
            System.out.println("Discount updated successfully!");
        } catch (DiscountNotFoundException e) {
            System.out.println("Discount not found with id " + discountId.value());
        }

        this.showMenu();
    }

    public void delete() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the discount to delete: ");
        final DiscountId discountId = new DiscountId(UUID.fromString(id));

        if (!discountService.existsById(discountId)) {
            System.out.println("The discount does not exist!");
            return;
        }

        discountService.delete(discountId);
        System.out.println("Discount deleted successfully!");
        this.showMenu();
    }

    public void changeStatus() {
        clearBuffer();
        final String id = scanString("Please enter the ID of the discount to change its status: ");
        final DiscountId discountId = new DiscountId(UUID.fromString(id));

        if (!discountService.existsById(discountId)) {
            System.out.println("The discount does not exist!");
            return;
        }

        final List<DiscountStatus> discountStatuses = Arrays.asList(DiscountStatus.values());
        for (int i = 0; i < discountStatuses.size(); i++) {
            System.out.println(i + "- " + discountStatuses.get(i));
        }
        final int statusId = scanInt("Enter the number of the new discount status: ");
        discountService.changeStatus(discountId, discountStatuses.get(statusId));
        System.out.println("Discount status changed successfully!");
        this.showMenu();
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    private String getTable(List<DiscountResponse> discounts) {
        return AsciiTable.getTable(discounts, Arrays.asList(
                new Column().with(discountResponse -> discountResponse.id().value().toString()),
                new Column().header("Contract ID").with(discountResponse -> discountResponse.contract().id().value().toString()),
                new Column().header("Name").with(DiscountResponse::name),
                new Column().header("Description").with(DiscountResponse::description),
                new Column().header("Conditions").with(DiscountResponse::conditions),
                new Column().header("Reduction").with(discountResponse -> discountResponse.reduction().toString()),
                new Column().header("Started At").with(discountResponse -> discountResponse.startedAt().toString()),
                new Column().header("Ends At").with(discountResponse -> discountResponse.endsAt().toString()),
                new Column().header("Status").with(discountResponse -> discountResponse.status().toString()),
                new Column().header("Created At").with(discountResponse -> discountResponse.createdAt() != null ? discountResponse.createdAt().toString() : "not saved"),
                new Column().header("Updated At").with(discountResponse -> discountResponse.updatedAt() != null ? discountResponse.updatedAt().toString() : "Not Updated Yet")
        ));
    }

    private Optional<ContractId> parseAndValidateContractId(String id) {
        final ContractId contractId = new ContractId(UUID.fromString(id));
        if (!contractService.existsById(contractId)) return Optional.empty();

        return Optional.of(contractId);
    }

    private Reduction scanReduction(String prompt) {
        String input = scanString(prompt);
        if (input.endsWith("%")) {
            Float percentage = Float.parseFloat(input.substring(0, input.length() - 1));
            return new Reduction(percentage, ReductionType.PERCENTAGE);
        } else if (input.toUpperCase().endsWith("EUR")) {
            Float amount = Float.parseFloat(input.substring(0, input.length() - 3));
            return new Reduction(amount, ReductionType.FIXED_PRICE);
        } else {
            System.out.println("Invalid reduction format. Please use '10%' for percentage or '50EUR' for fixed amount.");
            return scanReduction(prompt);
        }
    }
}