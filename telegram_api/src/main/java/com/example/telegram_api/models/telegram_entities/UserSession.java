package com.example.telegram_api.models.telegram_entities;

import com.example.telegram_api.enums.States;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSession {
    private Long chatId;
    private States state;
    private String email;
    private String password;
    private String ticker;
    private Double price;
    private Boolean auth;
}
