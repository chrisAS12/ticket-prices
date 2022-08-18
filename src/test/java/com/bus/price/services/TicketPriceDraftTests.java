package com.bus.price.services;

import com.bus.price.dto.PassengerDto;
import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TicketPriceDraftTests {

    TicketPriceDrafterService priceDrafter;
    @BeforeEach
    public void setup() {
        priceDrafter = new TicketPriceDrafterService();
    }

    @Test
    @DisplayName("Testing given example")
    void test1(){
        TicketDto ticketDto = new TicketDto();
        ticketDto.passengers = new ArrayList<>();
        PassengerDto passenger1 = new PassengerDto();
        passenger1.bagCount = 2;
        passenger1.isChild = false;
        ticketDto.passengers.add(passenger1);

        PassengerDto passenger2 = new PassengerDto();
        passenger2.bagCount = 1;
        passenger2.isChild = true;
        ticketDto.passengers.add(passenger2);

        TicketPriceDto ticketPricesModel = new TicketPriceDto();
        ticketPricesModel.totalPrice = "29.04 EUR";

        TicketPriceDto result = priceDrafter.getTicketPrices(ticketDto, 10.0, 21);
        assert result.totalPrice.equals(ticketPricesModel.totalPrice);
    }

    @Test
    @DisplayName("Testing with one adult and two bags")
    void test2(){
        TicketDto ticketDto = new TicketDto();
        ticketDto.passengers = new ArrayList<>();
        PassengerDto passenger1 = new PassengerDto();
        passenger1.bagCount = 2;
        passenger1.isChild = false;
        ticketDto.passengers.add(passenger1);

        TicketPriceDto ticketPricesModel = new TicketPriceDto();
        ticketPricesModel.totalPrice = "20.02 EUR";

        TicketPriceDto result = priceDrafter.getTicketPrices(ticketDto, 10.0, 25.1);
        assert result.totalPrice.equals(ticketPricesModel.totalPrice);
    }
}
