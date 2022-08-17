package com.bus.price.services;

import com.bus.price.domain.Route;
import com.bus.price.domain.TaxRate;
import com.bus.price.models.PassengerModel;
import com.bus.price.models.TicketModel;
import com.bus.price.repository.RouteRepository;
import com.bus.price.repository.TaxRateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.logging.Logger;

@Service
public class BusPriceService implements IBusPriceService {

    private static final Logger _logger = Logger.getLogger(BusPriceService.class.getName());

    private final TaxRateRepository _taxRateRepository;
    private final RouteRepository _routeRepository;

    public BusPriceService(TaxRateRepository taxRateRepository, RouteRepository routeRepository) {
        this._taxRateRepository = taxRateRepository;
        this._routeRepository = routeRepository;
    }

    @Override
    public String getPrice(TicketModel ticket) {
        _logger.info("Calculating price for route: " + ticket.routeName);

        double tax = 0.0;
        TaxRate taxRate = _taxRateRepository.findByTaxDate(LocalDate.now());
        if (taxRate == null) {
            _logger.warning("Tax rate not found for today! Using default tax rate.");
            tax = 21.0;
        } else {
            tax = taxRate.getTaxAmount().doubleValue();
        }

        double routePrice = 0;

        Route route = _routeRepository.findByName(ticket.routeName);
        if (route != null) {
            routePrice = route.getPrice().doubleValue();
        } else {
            _logger.warning("Route not found");
            return "Route not found";
        }

        double sum = 0;
        for (PassengerModel passenger :
                ticket.passengers) {
            sum += 0.3 * routePrice * passenger.bagCount;

            if (passenger.isChild) {
                sum += 0.5 * routePrice;
            }
            else{
                sum += routePrice;
            }
        }
        return (double)Math.round(((sum * (1 + tax / 100))*100d))/100d + " EUR";
    }

}

