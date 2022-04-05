package com.weather.advice.service;

import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GeocodingService {

    void saveCity(CityResponse city);

    Set<City> getCity(String name);

    List<CityResponse> getCityFromExternal(String name);

}
