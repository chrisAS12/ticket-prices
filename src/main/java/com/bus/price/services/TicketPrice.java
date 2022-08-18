package com.bus.price.services;

import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;

public interface TicketPrice {
    TicketPriceDto getPrice(TicketDto ticket);
}
