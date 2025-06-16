package com.univacinas.appointment;

import com.univacinas.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsAppointmentsByPatientAndStartDateTimeAndEndDateTimeAndStatus(User patient, LocalDateTime startDateTime, LocalDateTime endDateTime, AppointmentStatus status);

    @Query("""
          SELECT new com.univacinas.appointment.AppointmentStats(
            COUNT(a),
            COUNT(DISTINCT a.patient.id),
            COUNT(DISTINCT a.nurse.id)
          )
          FROM Appointment a
          WHERE a.creationDate BETWEEN :from AND :to
            AND (:patientId  IS NULL OR a.patient.id = :patientId)
            AND (:statusFilter IS NULL OR a.status = :statusFilter)
        """)
    AppointmentStats fetchStats(LocalDateTime from, LocalDateTime to, Long patientId, AppointmentStatus statusFilter);

    @Query("""
          SELECT new com.univacinas.appointment.AppointmentStatusReport(
            a.status,
            COUNT(a)
          )
          FROM Appointment a
          WHERE a.creationDate BETWEEN :from AND :to
            AND (:patientId    IS NULL OR a.patient.id = :patientId)
            AND (:statusFilter IS NULL OR a.status = :statusFilter)
          GROUP BY a.status
        """)
    List<AppointmentStatusReport> fetchCountByStatus(LocalDateTime from, LocalDateTime to, Long patientId, AppointmentStatus statusFilter);
}
