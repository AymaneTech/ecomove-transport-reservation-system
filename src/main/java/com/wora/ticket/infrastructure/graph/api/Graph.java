package com.wora.ticket.infrastructure.graph.api;

import java.util.List;

public interface Graph<T, E> {
    void addVertex(Vertex<T> vertex);

    void removeVertex(Vertex<T> vertex);

    void addEdge(Vertex<T> source, Vertex<T> destination, E data);

    void removeEdge(Edge<T, E> edge);

    List<Vertex<T>> getVertices();

    List<Edge<T, E>> getEdges();

    List<Edge<T, E>> getEdgesFrom(Vertex<T> vertex);

    List<Edge<T, E>> getEdgesTo(Vertex<T> vertex);

    Vertex<T> getVertex(String id);

    boolean hasEdge(Vertex<T> source, Vertex<T> destination);

    int getVertexCount();

    int getEdgeCount();
}
