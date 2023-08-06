package com.example.telegram_api.models.telegram_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Message {
    @JsonProperty("message")
    private String message;
    @JsonProperty("profileName")
    private String profileName;
}
