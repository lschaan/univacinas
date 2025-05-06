package com.univacinas.appointment;

import com.univacinas.user.User;
import com.univacinas.vaccine.Vaccine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User patient;

    @ManyToOne
    private User nurse;

    @ManyToOne
    private Vaccine vaccine;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private LocalDateTime dateTime;
    private LocalDateTime creationDate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

}
