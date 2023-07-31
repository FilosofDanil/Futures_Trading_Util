package com.example.binance_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Alerts {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("ticker")
    private String ticker;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("current_price")
    private Double current_price;
    @JsonProperty("alert_date")
    private Date alert_date;
    @JsonProperty("cross_date")
    private Date cross_date;
    @JsonProperty("crossed")
    private Boolean crossed;
    @JsonProperty("user")
    private Users user;
}
