package com.wora.ticket.domain.entities;

import com.wora.ticket.domain.valueObjects.JourneyId;

public class Journey {
    private JourneyId id;
    private Station start;
    private Station end;
    private Double distance;

    public Journey(JourneyId id, Station start, Station end, Double distance) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public JourneyId getId() {
        return id;
    }

    public Journey setId(JourneyId id) {
        this.id = id;
        return this;
    }

    public Station getStart() {
        return start;
    }

    public Journey setStart(Station start) {
        this.start = start;
        return this;
    }

    public Station getEnd() {
        return end;
    }

    public Journey setEnd(Station end) {
        this.end = end;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Journey setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
