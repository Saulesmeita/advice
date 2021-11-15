package com.weather.advice.service.impl;

import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import com.weather.advice.repository.CityRepository;
import com.weather.advice.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    private static final String WEATHER_URL = "http://api.openweathermap.org/geo/1.0/direct?q={city name}&limit=5&appid={API key}";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CityRepository repository;

    @Value("${api.openweathermap.key}")
    private String apiKey;

    @Override
    public void saveCity(City city) {
        repository.save(city);
    }

    @Override
    public Set<City> getCity(String name) {
        return repository.findCityByName(name);
    }

    @Override
    public List<CityResponse> getCityFromExternal(String name) {
        final URI url = new UriTemplate(WEATHER_URL).expand(name, apiKey);
        final CityResponse[] responses = restTemplate.getForObject(url, CityResponse[].class);
        return Arrays.asList(responses);
    }

}
