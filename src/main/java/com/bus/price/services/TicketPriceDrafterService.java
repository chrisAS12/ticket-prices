package com.bus.price.services;

import com.bus.price.dto.PassengerDto;
import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;

import java.util.ArrayList;

public class TicketPriceDrafterService implements TicketPriceDrafter {

    private double routePrice;
    private double additionalTax;

    private double roundValue(double value) {
        return Math.round(value * 100.0d) / 100.0d;
    }

    private double getPriceWithTaxAndRoutePrice(double additionalParameter) {
        return this.routePrice * this.additionalTax * additionalParameter;
    }

    private String getTicektDescription(double routePrice, double tax, double total) {
        return (" (" + routePrice + " EUR + " + tax + "%) = " + roundValue(total) + " EUR");
    }

    public TicketPriceDto getTicketPrices(TicketDto ticket, double routePrice, double tax) {
        this.additionalTax = 1 + (tax / 100);
        this.routePrice = routePrice;
        double sum = 0;
        TicketPriceDto tickets = new TicketPriceDto();
        tickets.ticketDescriptions = new ArrayList<>(ticket.passengers.size() * 2);

        for (PassengerDto passenger :
                ticket.passengers) {
            double currentSum = 0;
            if (passenger.isChild) {
                currentSum += getPriceWithTaxAndRoutePrice(0.5);
                tickets.ticketDescriptions.add("Child" +
                        getTicektDescription(routePrice, tax, currentSum));
            } else {
                currentSum += getPriceWithTaxAndRoutePrice(1);
                tickets.ticketDescriptions.add("Adult" +
                        getTicektDescription(routePrice, tax, currentSum));
            }

            sum += currentSum;
            currentSum = getPriceWithTaxAndRoutePrice(0.3 * passenger.bagCount);

            tickets.ticketDescriptions.add(passenger.bagCount + " bag(s) (" + routePrice + " EUR x 30% + " + tax + "%) = "
                    + roundValue(currentSum) + " EUR");
            sum += currentSum;

        }
        tickets.totalPrice = roundValue(sum) + " EUR";
        return tickets;
    }
}
