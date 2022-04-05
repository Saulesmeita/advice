package com.weather.advice;

import com.weather.advice.controller.GeocodingController;
import com.weather.advice.mapper.CityMapper;
import com.weather.advice.repository.CityRepository;
import com.weather.advice.service.GeocodingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdviceApplicationTests {

	@Autowired
	private CityRepository repository;

	@Autowired
	private GeocodingService service;

	@Autowired
	private CityMapper mapper;

	@Autowired
	private GeocodingController controller;

	/**
	 * When you build a Spring boot application using Spring Initializer, it auto creates a test class for you with
	 * contextLoads() empty method. Empty contextLoads() is a test to verify if the application is able to load
	 * Spring context successfully or not.
	 */
	@Test
	void contextLoads() {
		assertNotNull(repository);
		assertNotNull(service);
		assertNotNull(mapper);
		assertNotNull(controller);
	}
}
