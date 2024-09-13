package com.wora;


import com.wora.authentication.application.services.AuthenticationService;
import com.wora.authentication.application.services.impl.AuthenticationServiceImpl;
import com.wora.authentication.infrastructure.presentation.AuthenticationUi;
import com.wora.client.application.mappers.ClientMapper;
import com.wora.client.application.services.ClientService;
import com.wora.client.application.services.impl.ClientServiceImpl;
import com.wora.client.domain.repositories.ClientRepository;
import com.wora.client.infrastructure.mappers.ClientResultSetMapper;
import com.wora.client.infrastructure.persistence.ClientRepositoryImpl;
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
import com.wora.menu.infrastructure.presentation.AdministrationMenu;
import com.wora.menu.infrastructure.presentation.ChooseRoleMenu;
import com.wora.partner.application.mappers.PartnerMapper;
import com.wora.partner.application.services.PartnerService;
import com.wora.partner.application.services.impl.PartnerServiceImpl;
import com.wora.partner.domain.repositories.PartnerRepository;
import com.wora.partner.infrastcutre.mappers.PartnerResultSetMapper;
import com.wora.partner.infrastcutre.persistence.PartnerRepositoryImpl;
import com.wora.partner.infrastcutre.presentation.PartnerUi;
import com.wora.ticket.application.mappers.JourneyMapper;
import com.wora.ticket.application.mappers.StationMapper;
import com.wora.ticket.application.mappers.TicketMapper;
import com.wora.ticket.application.services.JourneyService;
import com.wora.ticket.application.services.StationService;
import com.wora.ticket.application.services.TicketService;
import com.wora.ticket.application.services.impl.JourneyServiceImpl;
import com.wora.ticket.application.services.impl.RouteFindingServiceImpl;
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
import com.wora.ticket.infrastructure.presentation.administration.JourneyUi;
import com.wora.ticket.infrastructure.presentation.administration.StationUi;
import com.wora.ticket.infrastructure.presentation.administration.TicketUi;


public class App {
    public static void main(String[] args) {
        final ChooseRoleMenu menu = getMainMenu();
        menu.showMenu();
//        final RouteFindingServiceImpl routeFindingService = (RouteFindingServiceImpl) getMainMenu();
//        routeFindingService.print();
    }


    private static ChooseRoleMenu getMainMenu() {
        final ClientRepository clientRepository = new ClientRepositoryImpl(new ClientResultSetMapper());
        final ClientMapper clientMapper = new ClientMapper();
        final ClientService clientService = new ClientServiceImpl(clientRepository, clientMapper);
        final AuthenticationService authenticationService = new AuthenticationServiceImpl(clientService, clientMapper);
        final AuthenticationUi authenticationUi = new AuthenticationUi(authenticationService);

        final PartnerRepository partnerRepository = new PartnerRepositoryImpl(new PartnerResultSetMapper());
        final PartnerService partnerService = new PartnerServiceImpl(partnerRepository, new PartnerMapper());
        final PartnerUi partnerUi = new PartnerUi(partnerService);

        final ContractRepository contractRepository = new ContractRepositoryImpl(new ContractResultSetMapper());
        final ContractService contractService = new ContractServiceImpl(contractRepository, partnerService, new ContractMapper());
        final ContractUi contractUi = new ContractUi(contractService, partnerService);

        final DiscountRepository discountRepository = new DiscountRepositoryImpl(new DiscountResultSetMapper());
        final DiscountService discountService = new DiscountServiceImpl(discountRepository, contractService, new DiscountMapper());
        final DiscountUi discountUi = new DiscountUi(discountService, contractService);

        final StationResultSetMapper stationResultSetMapper = new StationResultSetMapper();
        final StationRepository stationRepository = new StationRepositoryImpl(stationResultSetMapper);
        final StationService stationService = new StationServiceImpl(stationRepository, new StationMapper());
        final StationUi stationUi = new StationUi(stationService);

        final JourneyResultSetMapper journeyResultSetMapper = new JourneyResultSetMapper(stationResultSetMapper);
        final JourneyRepository journeyRepository = new JourneyRepositoryImpl(journeyResultSetMapper);
        final JourneyMapper journeyMapper = new JourneyMapper();
        final JourneyService journeyService = new JourneyServiceImpl(journeyRepository, stationService, journeyMapper);
        final JourneyUi journeyUi = new JourneyUi(journeyService);

        final TicketRepository ticketRepository = new TicketRepositoryImpl(new TicketResultSetMapper(journeyResultSetMapper));
        final TicketService ticketService = new TicketServiceImpl(ticketRepository, journeyService, contractService, new TicketMapper(journeyMapper));
        final TicketUi ticketUi = new TicketUi(ticketService, contractService);

        RouteFindingServiceImpl routeFindingService = new RouteFindingServiceImpl(stationRepository, journeyRepository, ticketRepository);


        final AdministrationMenu administrationMenu = new AdministrationMenu(partnerUi, contractUi, discountUi, ticketUi, stationUi, journeyUi);
        partnerUi.setMenu(administrationMenu);
        contractUi.setMenu(administrationMenu);
        discountUi.setMenu(administrationMenu);
        ticketUi.setMenu(administrationMenu);
        stationUi.setMenu(administrationMenu);
        journeyUi.setMenu(administrationMenu);

        final ChooseRoleMenu mainMenu = new ChooseRoleMenu(administrationMenu, authenticationUi);
        administrationMenu.setMenu(mainMenu);
        return mainMenu;
    }
}

