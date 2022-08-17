package com.bus.price.utils;

import com.bus.price.models.PassengerModel;
import com.bus.price.models.TicketModel;
import com.bus.price.models.TicketPricesModel;

import java.util.ArrayList;

public class TicketPriceDrafter {

    public TicketPricesModel getTicketPrices(TicketModel ticket, double routePrice, double tax) {
        double formattedTax = 1 + (tax / 100);
        double sum = 0;
        TicketPricesModel tickets = new TicketPricesModel();
        tickets.ticketDescriptions = new ArrayList<>(ticket.passengers.size()*2);

        for (PassengerModel passenger :
                ticket.passengers) {
            double currentSum = 0;
            if (passenger.isChild) {
                currentSum += 0.5 * routePrice * formattedTax;
                tickets.ticketDescriptions.add("Child (" + routePrice + " EUR + " + tax + "%) = "
                        + (double) Math.round(currentSum*100d)/100d + " EUR");
            }
            else{
                currentSum += routePrice * formattedTax;
                tickets.ticketDescriptions.add("Adult (" + routePrice + " EUR + " + tax + "%) = "
                        + (double) Math.round(currentSum*100d)/100d + " EUR");
            }

            sum += currentSum;
            currentSum = 0.3 * routePrice * passenger.bagCount * formattedTax;

            tickets.ticketDescriptions.add(passenger.bagCount + " bag(s) (" + routePrice + " EUR x 30% + " + tax + "%) = "
                    + (double) Math.round(currentSum*100d)/100d + " EUR");
            sum+= currentSum;

        }
        tickets.totalPrice = (double)Math.round((sum * 100d))/100d + " EUR";
        return tickets;
    }
}
