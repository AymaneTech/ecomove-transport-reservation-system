package com.wora;


import com.wora.contract.application.mappers.ContractMapper;
import com.wora.contract.application.services.ContractService;
import com.wora.contract.application.services.impl.ContractServiceImpl;
import com.wora.contract.domain.repositories.ContractRepository;
import com.wora.contract.infrastructure.mappers.ContractResultSetMapper;
import com.wora.contract.infrastructure.persistence.ContractRepositoryImpl;
import com.wora.contract.infrastructure.presentation.ContractUi;
import com.wora.menu.infrastructure.presentation.MainMenu;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.application.services.impl.PartnerServiceImpl;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;
import com.wora.partner.infrastcutre.persistence.PartnerRepositoryImpl;
import com.wora.partner.infrastcutre.presentation.PartnerUi;

import java.awt.*;

public class App {
    public static void main(String[] args) {

        final PartnerRepository partnerRepository = new PartnerRepositoryImpl(new PartnerResultSetMapper());
        final PartnerService partnerService = new PartnerServiceImpl(partnerRepository, new PartnerMapper());
        final PartnerUi partnerUi = new PartnerUi(partnerService);

        final ContractRepository contractRepository = new ContractRepositoryImpl(new ContractResultSetMapper());
        final ContractService contractService = new ContractServiceImpl(contractRepository, partnerService, new ContractMapper());
        final ContractUi contractUi = new ContractUi(contractService, partnerService);


        final MainMenu menu =  new MainMenu(partnerUi, contractUi);

        partnerUi.setMenu(menu);

        menu.showMenu();

    }
}
