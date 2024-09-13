package com.wora.ticket.infrastructure.graph.adapters;

import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.infrastructure.graph.api.Edge;
import com.wora.ticket.infrastructure.graph.api.Vertex;

public class JourneyEdge implements Edge<Station, Journey> {
    private Journey journey;
    private final Vertex<Station> source;
    private final Vertex<Station> destination;

    public JourneyEdge(Journey journey) {
        this.journey = journey;
        this.source = new StationVertex(journey.getStart());
        this.destination = new StationVertex(journey.getEnd());
    }

    @Override
    public Vertex<Station> getSource() {
        return source;
    }

    @Override
    public Vertex<Station> getDestination() {
        return destination;
    }

    @Override
    public Journey getData() {
        return journey;
    }

    @Override
    public void setData(Journey data) {
        this.journey = data;
    }

    @Override
    public Double getWeight() {
        return journey.getDistance();
    }

    @Override
    public void setWeight(Double weight) {
       this.journey.setDistance(weight);
    }
}
