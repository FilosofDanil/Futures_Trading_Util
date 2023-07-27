package com.example.telegram_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("profileName")
    private String name;
    @JsonProperty("password")
    private String password;
}
