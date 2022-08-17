package com.bus.price.services;

import com.bus.price.models.TicketModel;
import com.bus.price.models.TicketPricesModel;

public interface IBusPriceService {
    TicketPricesModel getPrice(TicketModel ticket);
}
