package com.weather.advice.dto.response.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponse {

    String name;
    @JsonProperty("lat")
    BigDecimal latitude;
    @JsonProperty("lon")
    BigDecimal longitude;
    String country;
}

