package com.wora.ticket.domain.entities;

import com.wora.common.domain.AbstractEntity;
import com.wora.ticket.domain.valueObjects.StationId;

import java.util.List;

public class Station extends AbstractEntity<StationId> {
    private StationId id;
    private String name;
    private String city;
    private List<Journey> journeys;

    public Station(StationId id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public StationId getId() {
        return id;
    }

    public Station setId(StationId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Station setCity(String city) {
        this.city = city;
        return this;
    }

    public List<Journey> getTrajects() {
        return journeys;
    }

    public Station setTrajects(List<Journey> journeys) {
        this.journeys = journeys;
        return this;
    }
}
