package com.univacinas.appointment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentRequest {

    @NotNull
    private Long patientId;

    @NotNull
    private Long vaccineId;

    @NotNull
    private LocalDateTime dateTime;
}
