package com.wora;


import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.application.services.impl.ContractServiceImpl;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.contract.infrastructure.mappers.ContractResultSetMapper;
import com.wora.contract.infrastructure.persistence.ContractRepositoryImpl;
import com.wora.contract.infrastructure.presentation.ContractUi;
import com.wora.discount.application.mappers.DiscountMapper;
import com.wora.discount.application.services.DiscountService;
import com.wora.discount.application.services.impl.DiscountServiceImpl;
import com.wora.discount.domain.repositories.DiscountRepository;
import com.wora.discount.infrastructure.mappers.DiscountResultSetMapper;
import com.wora.discount.infrastructure.persistence.DiscountRepositoryImpl;
import com.wora.discount.infrastructure.presentation.DiscountUi;
import com.wora.menu.infrastructure.presentation.MainMenu;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.application.services.impl.PartnerServiceImpl;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;
import com.wora.partner.infrastcutre.persistence.PartnerRepositoryImpl;
import com.wora.partner.infrastcutre.presentation.PartnerUi;

public class App {
    public static void main(String[] args) {

        final MainMenu menu = getMainMenu();

        menu.showMenu();

    }

    private static MainMenu getMainMenu() {
        final PartnerRepository partnerRepository = new PartnerRepositoryImpl(new PartnerResultSetMapper());
        final PartnerService partnerService = new PartnerServiceImpl(partnerRepository, new PartnerMapper());
        final PartnerUi partnerUi = new PartnerUi(partnerService);

        final ContractRepository contractRepository = new ContractRepositoryImpl(new ContractResultSetMapper());
        final ContractService contractService = new ContractServiceImpl(contractRepository, partnerService, new ContractMapper());
        final ContractUi contractUi = new ContractUi(contractService, partnerService);

        final DiscountRepository discountRepository = new DiscountRepositoryImpl(new DiscountResultSetMapper());
        final DiscountService discountService = new DiscountServiceImpl(discountRepository, contractService, new DiscountMapper());
        final DiscountUi discountUi = new DiscountUi(discountService, contractService);


        final MainMenu menu = new MainMenu(partnerUi, contractUi, discountUi);
        partnerUi.setMenu(menu);
        contractUi.setMenu(menu);
        discountUi.setMenu(menu);
        return menu;
    }
}
