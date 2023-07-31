package com.example.binance_api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Users {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("user_password")
    private String password;
    @JsonProperty("profile_name")
    private String profile_name;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("activation_code")
    private String activationCode;
}
