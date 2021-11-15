package com.weather.advice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    Long id;
    String name;
    BigDecimal lat;
    BigDecimal lon;
    String country;
}
