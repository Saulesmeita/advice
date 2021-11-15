package com.weather.advice.dto.response.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponse {

    String name;
    BigDecimal lat;
    BigDecimal lon;
    String country;
}

