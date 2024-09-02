package com.wora;


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
        final PartnerMapper partnerMapper = new PartnerMapper();
        final PartnerService partnerService = new PartnerServiceImpl(partnerRepository, partnerMapper);
        final PartnerUi partnerUi = new PartnerUi(partnerService);
        final MainMenu menu =  new MainMenu(partnerUi);

        menu.showMenu();

    }
}
