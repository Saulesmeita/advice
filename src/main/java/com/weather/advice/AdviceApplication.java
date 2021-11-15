package com.weather.advice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdviceApplication.class, args);
		System.out.println("Application started");
		System.out.println("H2 console is available at: http://localhost:8080/h2");
		System.out.println("Swagger documentation: http://localhost:8080/swagger-ui.html");
	}
}
