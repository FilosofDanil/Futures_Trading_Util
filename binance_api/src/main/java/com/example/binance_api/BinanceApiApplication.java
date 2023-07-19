package com.example.binance_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BinanceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BinanceApiApplication.class, args);
    }

}
