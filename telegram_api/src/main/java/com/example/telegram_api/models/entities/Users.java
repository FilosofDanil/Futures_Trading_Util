package com.example.telegram_api.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("profileName")
    private String profileName;
    @JsonProperty("verified")
    private Boolean verified;
    @JsonProperty("activationCode")
    private String activationCode;
}
