package com.univacinas.appointment;

import com.univacinas.user.UserResponse;
import com.univacinas.vaccine.Vaccine;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;

    private UserResponse patient;
    private UserResponse nurse;

    private Vaccine vaccine;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private LocalDateTime creationDate;

    public static AppointmentResponse from(Appointment appointment) {
        return AppointmentResponse.builder()
            .id(appointment.getId())
            .patient(UserResponse.from(appointment.getPatient()))
            .nurse(UserResponse.from(appointment.getNurse()))
            .vaccine(appointment.getVaccine())
            .status(appointment.getStatus())
            .startDateTime(appointment.getStartDateTime())
            .endDateTime(appointment.getEndDateTime())
            .creationDate(appointment.getCreationDate())
            .build();
    }
}
