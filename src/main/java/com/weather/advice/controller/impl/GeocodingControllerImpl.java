package com.weather.advice.controller.impl;

import com.weather.advice.controller.GeocodingController;
import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import com.weather.advice.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
public class GeocodingControllerImpl implements GeocodingController {

    @Autowired
    GeocodingService service;

    @Override
    public void add(@Valid CityResponse city) {
        service.saveCity(city);
    }

    @Override
    public Set<City> getByName(String name) {
        return service.getCity(name);
    }

    @Override
    public List<CityResponse> fetchByNameFromExternal(String name) {
        return service.getCityFromExternal(name);
    }
}
