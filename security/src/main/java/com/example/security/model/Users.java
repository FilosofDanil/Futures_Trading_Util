package com.example.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
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
