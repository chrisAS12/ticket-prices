package com.bus.price.services;

import com.bus.price.domain.Route;
import com.bus.price.domain.TaxRate;
import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;
import com.bus.price.repository.RouteRepository;
import com.bus.price.repository.TaxRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class TicketPriceService implements TicketPrice {

    private static final Logger LOGGER = Logger.getLogger(TicketPriceService.class.getName());

    private final TaxRateRepository taxRateRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public TicketPriceService(TaxRateRepository taxRateRepository,
                              RouteRepository routeRepository) {
        this.taxRateRepository = taxRateRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public TicketPriceDto getPrice(TicketDto ticket) {
        LOGGER.info("Calculating price for route: " + ticket.routeName);

        double tax;
        TaxRate taxRate = taxRateRepository.findByTaxDate(LocalDate.now());
        if (taxRate == null) {
            LOGGER.warning("Tax rate not found for today! Using default tax rate.");
            tax = 21.0;
        } else {
            tax = taxRate.getTaxAmount().doubleValue();
        }

        double routePrice = 0;
        Route route = routeRepository.findByName(ticket.routeName);
        if (route != null) {
            routePrice = route.getPrice().doubleValue();
        } else {
            LOGGER.warning("Route not found");
            return null;
        }
        TicketPriceDrafterService calculator = new TicketPriceDrafterService();
        TicketPriceDto tickets = calculator.getTicketPrices(ticket, routePrice, tax);

        return tickets;
    }

}

