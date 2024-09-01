package com.wora.ticket.domain.valueObjects;

import java.util.Currency;

public record Price(
        Float amount,
        Currency currency
) {
}
