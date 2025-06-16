package com.univacinas.report;

import com.univacinas.appointment.AppointmentRepository;
import com.univacinas.appointment.AppointmentStats;
import com.univacinas.appointment.AppointmentStatus;
import com.univacinas.appointment.AppointmentStatusReport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentReportService {

    private final AppointmentRepository appointmentRepository;


    public AppointmentReportResponse getAppointmentReport(LocalDateTime from, LocalDateTime to, Long patientId, AppointmentStatus status) {
        AppointmentStats stats = appointmentRepository.fetchStats(from, to, patientId, status);
        List<AppointmentStatusReport> byStatus = appointmentRepository.fetchCountByStatus(from, to, patientId, status);

        return AppointmentReportResponse.builder()
            .generatedAt(LocalDateTime.now())
            .from(from.toLocalDate())
            .to(to.toLocalDate())
            .totalAppointments(stats.getTotalAppointments())
            .distinctNurses(stats.getDistinctNurses())
            .distinctPatients(stats.getDistinctPatients())
            .byStatus(byStatus)
            .build();
    }

}
