package com.wora.ticket.application.dtos.requests;

public record UpdateJourneyDto(String startCityName, String endCityName, Double distance) {
}
