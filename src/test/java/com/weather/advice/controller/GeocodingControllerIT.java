package com.weather.advice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import com.weather.advice.repository.CityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GeocodingControllerIT {

    private static final String CITY_NAME = "Riga";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository repository;

    @Test
    @DisplayName("Should add the city to local DB successfully")
    void shouldAddTheCityToDbSuccessfully() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CityResponse cityResponse = CityResponse.builder()
                .name(CITY_NAME)
                .country("LV")
                .build();
        String json = mapper.writeValueAsString(cityResponse);

        mockMvc.perform(put("/city/save/").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        Set<City> citySet = repository.findCityByName(CITY_NAME);
        assertEquals(1, citySet.size());
        assertEquals(CITY_NAME, citySet.iterator().next().getName());
    }

    @Test
    @DisplayName("Should return status 200 (OK) when getting City from local DB")
    void getByNameShouldReturnOk() throws Exception {
        mockMvc
                .perform(
                        get("/city/" + CITY_NAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
