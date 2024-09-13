package com.wora.ticket.application.dtos.requests;

import java.time.LocalDate;

public record SearchForTicketDto(String startStation, String endStation, LocalDate date) {
}
