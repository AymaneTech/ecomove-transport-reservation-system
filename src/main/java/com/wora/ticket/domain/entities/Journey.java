package com.wora.ticket.domain.entities;

import com.wora.ticket.domain.valueObjects.TrajectId;

public class Traject {
    private TrajectId id;
    private Station start;
    private Station end;
    private Double distance;

    public Traject(TrajectId id, Station start, Station end, Double distance) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public TrajectId getId() {
        return id;
    }

    public Traject setId(TrajectId id) {
        this.id = id;
        return this;
    }

    public Station getStart() {
        return start;
    }

    public Traject setStart(Station start) {
        this.start = start;
        return this;
    }

    public Station getEnd() {
        return end;
    }

    public Traject setEnd(Station end) {
        this.end = end;
        return this;
    }

    public Double getDistance() {
        return distance;
    }

    public Traject setDistance(Double distance) {
        this.distance = distance;
        return this;
    }
}
