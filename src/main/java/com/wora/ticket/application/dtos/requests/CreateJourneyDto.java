package com.wora.ticket.application.dtos.requests;

public record CreateJourneyDto(String startCityName, String endCityName, Double distance) {
}
