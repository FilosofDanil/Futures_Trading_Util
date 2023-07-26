package com.example.binance_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Alerts {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("ticker")
    private String ticker;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("alert_date")
    private Date date;
    @JsonProperty("cross_date")
    private Date cross_date;
    @JsonProperty("crossed")
    private Boolean crossed;
    @JsonIgnore
    private Users user;
}