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
import com.wora.journey.application.mappers.JourneyMapper;
import com.wora.menu.infrastructure.presentation.MainMenu;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.application.services.impl.PartnerServiceImpl;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;
import com.wora.partner.infrastcutre.persistence.PartnerRepositoryImpl;
import com.wora.partner.infrastcutre.presentation.PartnerUi;
import com.wora.ticket.application.mappers.StationMapper;
import com.wora.ticket.application.mappers.TicketMapper;
import com.wora.ticket.application.services.JourneyService;
import com.wora.ticket.application.services.StationService;
import com.wora.ticket.application.services.TicketService;
import com.wora.ticket.application.services.impl.JourneyServiceImpl;
import com.wora.ticket.infrastructure.presentation.JourneyUi;
import com.wora.ticket.application.services.impl.StationServiceImpl;
import com.wora.ticket.application.services.impl.TicketServiceImpl;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.domain.repositories.TicketRepository;
import com.wora.ticket.infrastructure.mappers.JourneyResultSetMapper;
import com.wora.ticket.infrastructure.mappers.StationResultSetMapper;
import com.wora.ticket.infrastructure.mappers.TicketResultSetMapper;
import com.wora.ticket.infrastructure.persistence.JourneyRepositoryImpl;
import com.wora.ticket.infrastructure.persistence.StationRepositoryImpl;
import com.wora.ticket.infrastructure.persistence.TicketRepositoryImpl;
import com.wora.ticket.infrastructure.presentation.StationUi;
import com.wora.ticket.infrastructure.presentation.TicketUi;


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

        final TicketRepository ticketRepository = new TicketRepositoryImpl(new TicketResultSetMapper());
        final TicketService ticketService = new TicketServiceImpl(ticketRepository, contractService, new TicketMapper());
        final TicketUi ticketUi = new TicketUi(ticketService, contractService);

        final StationResultSetMapper stationResultSetMapper = new StationResultSetMapper();
        final StationRepository stationRepository = new StationRepositoryImpl(stationResultSetMapper);
        final StationService stationService = new StationServiceImpl(stationRepository, new StationMapper());
        final StationUi stationUi = new StationUi(stationService);

        final JourneyRepository journeyRepository = new JourneyRepositoryImpl(new JourneyResultSetMapper(stationResultSetMapper));
        final JourneyService journeyService = new JourneyServiceImpl(journeyRepository, stationRepository, new JourneyMapper());
        final JourneyUi journeyUi = new JourneyUi(journeyService);


        final MainMenu menu = new MainMenu(partnerUi, contractUi, discountUi, ticketUi, stationUi, journeyUi);
        partnerUi.setMenu(menu);
        contractUi.setMenu(menu);
        discountUi.setMenu(menu);
        ticketUi.setMenu(menu);
        stationUi.setMenu(menu);
        journeyUi.setMenu(menu);
        return menu;
    }
}

