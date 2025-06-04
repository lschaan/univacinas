package com.univacinas.appointment;

import com.univacinas.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsAppointmentsByPatientAndStartDateTimeAndEndDateTimeAndStatus(User patient, LocalDateTime startDateTime, LocalDateTime endDateTime, AppointmentStatus status);

    List<Appointment> findAppointmentByPatient_Id(Long patientId);
}
