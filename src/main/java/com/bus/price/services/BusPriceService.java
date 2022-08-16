package com.bus.price.services;

import com.bus.price.models.TicketModel;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class BusPriceService implements IBusPriceService {

    private static final Logger _logger = Logger.getLogger(BusPriceService.class.getName());

    public double getPrice(TicketModel ticket) {
        _logger.info("Calculating price for route: " + ticket.routeName);
        _logger.info(ticket.toString());
        return 0.0;
    }

}

