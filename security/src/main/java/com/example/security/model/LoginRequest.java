package com.example.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    @JsonProperty("email")
    private String email;
    @JsonProperty("profileName")
    private String profileName;
    @JsonProperty("password")
    private String password;
}
