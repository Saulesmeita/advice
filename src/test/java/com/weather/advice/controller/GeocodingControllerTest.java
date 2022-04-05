package com.weather.advice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.advice.controller.impl.GeocodingControllerImpl;
import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import com.weather.advice.service.GeocodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GeocodingControllerTest {

    private static final String CITY_NAME = "CityName";

    @Mock
    private GeocodingService serviceMock;

    @InjectMocks
    private GeocodingControllerImpl controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Should add the city to local DB successfully")
    void shouldAddTheCityToDbSuccessfully() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CityResponse cityResponse = CityResponse.builder().build();
        String json = mapper.writeValueAsString(cityResponse);

        mockMvc.perform(put("/city/save/").contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk());

        verify(serviceMock).saveCity(cityResponse);
    }

    @Test
    @DisplayName("Should return status 200 (OK) when getting City from local DB")
    void getByNameShouldReturnOk() throws Exception {
        when(serviceMock.getCity(any())).thenReturn(Set.of(new City()));
        mockMvc
                .perform(
                        get("/city/" + CITY_NAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(serviceMock).getCity(CITY_NAME);
    }

    @Test
    @DisplayName("Should return status 200 (OK) when fetching City by name from external API")
    void fetchByNameFromExternalShouldReturnOk() throws Exception {
        when(serviceMock.getCityFromExternal(any())).thenReturn(List.of(CityResponse.builder().build()));
        mockMvc
                .perform(
                        get("/city/external/" + CITY_NAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(serviceMock).getCityFromExternal(CITY_NAME);
    }

    @Test
    @DisplayName("Should return OK when fetching City from external API and the resulting list is empty")
    void fetchByNameFromExternalShouldReturnOkOnEmptyList() throws Exception {
        when(serviceMock.getCityFromExternal(any())).thenReturn(List.of());
        mockMvc
                .perform(
                        get("/city/external/" + CITY_NAME)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(serviceMock).getCityFromExternal(CITY_NAME);
    }
}
