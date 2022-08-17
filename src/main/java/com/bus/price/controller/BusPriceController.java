package com.bus.price.controller;

import com.bus.price.models.TicketModel;
import com.bus.price.services.IBusPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BusPriceController {

    private final IBusPriceService _busPriceService;

    public BusPriceController(IBusPriceService busPriceService) {
        this._busPriceService = busPriceService;
    }

    @GetMapping("/getPrice")
    public ResponseEntity<String> getPrice(@RequestBody TicketModel ticketModel) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(_busPriceService.getPrice(ticketModel));
    }
}
