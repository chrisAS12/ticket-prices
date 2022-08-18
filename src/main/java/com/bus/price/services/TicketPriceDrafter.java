package com.bus.price.services;

import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;

public interface TicketPriceDrafter {
    TicketPriceDto getTicketPrices(TicketDto ticket, double routePrice, double tax);
}
