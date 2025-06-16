package com.univacinas.report;

import com.univacinas.appointment.AppointmentStatusReport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentReportResponse {

    private LocalDateTime generatedAt;

    private LocalDate from;
    private LocalDate to;

    private long totalAppointments;
    private long distinctPatients;
    private long distinctNurses;

    private List<AppointmentStatusReport> byStatus;

}
