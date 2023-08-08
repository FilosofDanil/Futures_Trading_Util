package com.example.binance_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    @JsonProperty("message")
    private String message;
    @JsonProperty("profileName")
    private String profileName;
}
