package com.bus.price.services;

import com.bus.price.models.PassengerModel;
import com.bus.price.models.TicketModel;
import com.bus.price.models.TicketPricesModel;
import com.bus.price.utils.TicketPriceDrafter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TicketPriceDraftTests {

    TicketPriceDrafter priceDrafter;
    @BeforeEach
    public void setup() {
        priceDrafter = new TicketPriceDrafter();
    }

    @Test
    @DisplayName("Testing given example")
    void test1(){
        TicketModel ticketModel = new TicketModel();
        ticketModel.passengers = new ArrayList<>();
        PassengerModel passenger1 = new PassengerModel();
        passenger1.bagCount = 2;
        passenger1.isChild = false;
        ticketModel.passengers.add(passenger1);

        PassengerModel passenger2 = new PassengerModel();
        passenger2.bagCount = 1;
        passenger2.isChild = true;
        ticketModel.passengers.add(passenger2);

        TicketPricesModel ticketPricesModel = new TicketPricesModel();
        ticketPricesModel.totalPrice = "29.04 EUR";

        TicketPricesModel result = priceDrafter.getTicketPrices(ticketModel, 10.0, 21);
        assert result.totalPrice.equals(ticketPricesModel.totalPrice);
    }

    @Test
    @DisplayName("Testing with one adult and two bags")
    void test2(){
        TicketModel ticketModel = new TicketModel();
        ticketModel.passengers = new ArrayList<>();
        PassengerModel passenger1 = new PassengerModel();
        passenger1.bagCount = 2;
        passenger1.isChild = false;
        ticketModel.passengers.add(passenger1);

        TicketPricesModel ticketPricesModel = new TicketPricesModel();
        ticketPricesModel.totalPrice = "20.02 EUR";

        TicketPricesModel result = priceDrafter.getTicketPrices(ticketModel, 10.0, 25.1);
        assert result.totalPrice.equals(ticketPricesModel.totalPrice);
    }
}
