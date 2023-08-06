package com.example.telegram_api.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}
