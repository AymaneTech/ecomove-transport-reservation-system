package com.wora.ticket.application.services.impl;

import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.domain.repositories.JourneyRepository;
import com.wora.ticket.domain.repositories.StationRepository;
import com.wora.ticket.domain.repositories.TicketRepository;
import com.wora.ticket.infrastructure.graph.TransportNetwork;
import com.wora.ticket.infrastructure.graph.adapters.StationVertex;
import com.wora.ticket.infrastructure.graph.api.Graph;

import java.sql.SQLOutput;
import java.util.List;

public class RouteFindingServiceImpl {
    private final Graph<Station, Journey> transportNetwork;
    private final StationRepository stationRepository;
    private final JourneyRepository journeyRepository;
    private final TicketRepository ticketRepository;

    public RouteFindingServiceImpl(StationRepository stationRepository, JourneyRepository journeyRepository, TicketRepository ticketRepository) {
        this.stationRepository = stationRepository;
        this.journeyRepository = journeyRepository;
        this.ticketRepository = ticketRepository;
        this.transportNetwork = new TransportNetwork();
        initializeGraph();
    }

    public void initializeGraph() {
        final List<Station> stations = this.stationRepository.findAll();
        final List<Journey> journeys = this.journeyRepository.findAll();

        stations.forEach(station -> transportNetwork.addVertex(new StationVertex(station)));
        journeys.forEach(transportNetwork::addEdge);
    }

    public void print() {
        System.out.println(transportNetwork.getEdgeCount());
        System.out.println(transportNetwork.getVertexCount());

        System.out.println("-------------------- vertices -----------------------");
        transportNetwork.getVertices().forEach(vertice -> System.out.println(vertice.getData().getCity()));

        System.out.println("-------------------------- edges -----------------");
        transportNetwork.getEdges().forEach(edge -> System.out.println(edge.getSource().getData().getCity() + " ---> " + edge.getDestination().getData().getCity()));



    }
}
