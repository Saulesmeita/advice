package com.weather.advice.mapper;

import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public City fromDto(CityResponse response) {
        return City.builder()
                .longitude(response.getLongitude())
                .latitude(response.getLatitude())
                .country(response.getCountry())
                .name(response.getName())
                .build();
    }
    public CityResponse toDto(City city) {
        return CityResponse.builder()
                .name(city.getName())
                .country(city.getCountry())
                .longitude(city.getLongitude())
                .latitude(city.getLatitude())
                .build();
    }
}
