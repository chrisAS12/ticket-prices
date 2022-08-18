package com.bus.price.controller;

import com.bus.price.dto.TicketDto;
import com.bus.price.dto.TicketPriceDto;
import com.bus.price.services.TicketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TicketPriceController {

    private final TicketPrice busPriceService;

    @Autowired
    public TicketPriceController(TicketPrice busPriceService) {
        this.busPriceService = busPriceService;
    }

    @PostMapping("/prices")
    public ResponseEntity<TicketPriceDto> getPrice(@RequestBody TicketDto ticketDto) {

        TicketPriceDto ticketPricesModel = busPriceService.getPrice(ticketDto);

        if (ticketPricesModel == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ticketPricesModel);
    }
}
