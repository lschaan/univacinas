package com.univacinas.vaccine;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateVaccineRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String batch;

    @NotBlank
    private String manufacturer;

    @NotNull
    @Future
    private LocalDate expirationDate;

    @Positive
    @NotNull
    private Integer amount;
}
