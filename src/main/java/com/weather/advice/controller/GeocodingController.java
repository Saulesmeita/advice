package com.weather.advice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weather.advice.dto.response.external.CityResponse;
import com.weather.advice.model.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Geocoding controller", tags = "Geocoding")
@RestController
@RequestMapping(value = "/city", produces = APPLICATION_JSON_VALUE)
public interface GeocodingController {

    @ApiOperation(value = "Save City to the DB",
            notes = "adds a new City entry to the local DB"
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    void add(@Valid @RequestBody City city);

    @ApiOperation(value = "Find City by name",
            notes = "Search local DB and return the list of Cities meeting the search criteria",
            response = City.class,
            responseContainer = "List"
    )
    @GetMapping("/{name}")
    Set<City> getByName(@ApiParam(value = "name", required = true, example = "London")
                   @PathVariable String name);

    @ApiOperation(value = "Find City by name using external API",
            notes = "Search the external API and return the list of Cities meeting the search criteria",
            response = CityResponse.class,
            responseContainer = "List"
    )
    @GetMapping("/external/{name}")
    List<CityResponse> fetchByNameFromExternal(@ApiParam(value = "name", required = true, example = "London")
                                         @PathVariable String name) throws JsonProcessingException;;
}
