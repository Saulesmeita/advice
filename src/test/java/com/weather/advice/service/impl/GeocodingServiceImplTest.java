package com.weather.advice.service.impl;

import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.mapper.CityMapper;
import com.weather.advice.model.City;
import com.weather.advice.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeocodingServiceImplTest {

    private static final String NAME = "Name";
    private static final String COUNTRY = "country";
    private static final String GB = "GB";
    private static final String LV = "LV";
    private static final BigDecimal LATITUDE = BigDecimal.ONE;
    private static final BigDecimal LONGITUDE = BigDecimal.TEN;

    @Mock
    private CityRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CityMapper mapper;

    @InjectMocks
    private GeocodingServiceImpl service;

    @Test
    void shouldSaveCitySuccessfully() {
        City city = createCity();
        CityResponse cityResponse = createCityResponse(GB);
        when(mapper.fromDto(any())).thenReturn(createCity());

        service.saveCity(cityResponse);

        verify(repository).save(city);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void shouldGetCityByNameSuccessfully() {
    when(repository.findCityByName(NAME)).thenReturn(Set.of(createCity()));

    Set<City> result = service.getCity(NAME);

    assertEquals(1, result.size());
    assertEquals(COUNTRY, result.iterator().next().getCountry());
    }

    @Test
    void shouldFetchCitiesFromExternalApiSuccessfully() {
        final CityResponse[] responses = {createCityResponse(LV), createCityResponse(GB)};
        when(restTemplate.getForObject(any(), any())).thenReturn(responses);

        List<CityResponse> result = service.getCityFromExternal(NAME);

        assertEquals(2, result.size());
        Set<String> countriesInResponse = result.stream()
                .map(CityResponse::getCountry)
                .collect(Collectors.toSet());
        assertEquals(Set.of(GB, LV), countriesInResponse);
    }

    private City createCity() {
         return City.builder()
                .country(COUNTRY)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .name(NAME)
                .build();
    }

    private CityResponse createCityResponse(String country) {
        return CityResponse.builder()
                .country(country)
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .name(NAME)
                .build();
    }
}