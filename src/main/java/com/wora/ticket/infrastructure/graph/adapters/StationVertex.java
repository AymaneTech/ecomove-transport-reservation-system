package com.wora.ticket.infrastructure.graph.adapters;

import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.infrastructure.graph.api.Vertex;

public class StationVertex implements Vertex<Station> {
    private Station station;

    public StationVertex(Station station) {
        this.station = station;
    }

    @Override
    public Station getData() {
        return station;
    }

    @Override
    public void setData(Station data) {
        this.station = data;
    }

    @Override
    public String getId() {
        return station.getId().value().toString();
    }
}
