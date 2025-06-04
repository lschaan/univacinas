package com.univacinas.appointment;

import com.univacinas.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsAppointmentsByPatientAndStartDateTimeAndEndDateTime(User patient, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
