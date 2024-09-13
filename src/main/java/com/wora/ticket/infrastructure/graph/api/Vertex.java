package com.wora.ticket.infrastructure.graph.api;

public interface Vertex<T> {
    T getData();

    void setData(T data);

    String getId();
}
