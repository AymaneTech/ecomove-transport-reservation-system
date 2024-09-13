package com.wora.ticket.infrastructure.graph.api;

public interface Edge<T, E> {

    Vertex<T> getSource();

    Vertex<T> getDestination();

    E getData();

    void setData(E data);

    Double getWeight();

    void setWeight(Double weight);
}
