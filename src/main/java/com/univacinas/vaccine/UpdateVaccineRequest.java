package com.univacinas.vaccine;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateVaccineRequest {

    private String name;
    private String batch;
    private String manufacturer;

    @Future
    private LocalDate expirationDate;

    @Positive
    private Integer amount;
}
