package com.example.binance_api.controllers;

import com.example.binance_api.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/binance/price")
public class PriceController {
    private final PriceService priceService;

    @GetMapping("/{marker}")
    public ResponseEntity<String> getPrice(@PathVariable("marker") String marker) {
        return new ResponseEntity<>(priceService.getPrice(marker), HttpStatus.OK);
    }
}
