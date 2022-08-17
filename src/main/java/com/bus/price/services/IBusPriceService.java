package com.bus.price.services;

import com.bus.price.models.TicketModel;

public interface IBusPriceService {
    String getPrice(TicketModel ticket);
}
