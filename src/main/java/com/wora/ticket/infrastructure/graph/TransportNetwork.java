package com.wora.ticket.infrastructure.graph;

import com.wora.ticket.domain.entities.Journey;
import com.wora.ticket.domain.entities.Station;
import com.wora.ticket.infrastructure.graph.adapters.JourneyEdge;
import com.wora.ticket.infrastructure.graph.adapters.StationVertex;
import com.wora.ticket.infrastructure.graph.api.Edge;
import com.wora.ticket.infrastructure.graph.api.Graph;
import com.wora.ticket.infrastructure.graph.api.Vertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransportNetwork implements Graph<Station, Journey> {
    private final Map<String, StationVertex> vertices = new HashMap<>();
    private final List<JourneyEdge> edges = new ArrayList<>();

    @Override
    public void addVertex(Vertex<Station> vertex) {
        vertices.put(vertex.getId(), (StationVertex) vertex);

    }

    @Override
    public void removeVertex(Vertex<Station> vertex) {
        vertices.remove(vertex.getId());
        edges.removeIf(e -> e.getSource().equals(vertex) || e.getDestination().equals(vertex));
    }

    @Override
    public void addEdge(Journey data) {
        JourneyEdge edge = new JourneyEdge(data);
        edges.add(edge);
    }

    @Override
    public void removeEdge(Edge<Station, Journey> edge) {
        edges.remove(edge);
    }

    @Override
    public List<Vertex<Station>> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    @Override
    public List<Edge<Station, Journey>> getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public List<Edge<Station, Journey>> getEdgesFrom(Vertex<Station> vertex) {
        return edges.stream()
                .filter(e -> e.getSource().getId().equals(vertex.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Edge<Station, Journey>> getEdgesTo(Vertex<Station> vertex) {
        return edges.stream()
                .filter(e -> e.getDestination().getId().equals(vertex.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Vertex<Station> getVertex(String id) {
        return vertices.get(id);
    }

    @Override
    public boolean hasEdge(Vertex<Station> source, Vertex<Station> destination) {
        return edges.stream()
                .anyMatch(e -> e.getSource().getId().equals(source.getId())
                        && e.getDestination().getId().equals(destination.getId()));
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    @Override
    public int getEdgeCount() {
        return vertices.size();
    }
}
