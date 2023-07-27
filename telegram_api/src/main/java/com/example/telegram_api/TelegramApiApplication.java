package com.example.telegram_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TelegramApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramApiApplication.class, args);
    }

}
