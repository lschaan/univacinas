package com.univacinas.report;

import com.univacinas.appointment.AppointmentStatus;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("/report")
@CrossOrigin
@AllArgsConstructor
public class ReportController {

    private final VaccineReportService vaccineReportService;
    private final AppointmentReportService appointmentReportService;

    @GetMapping("/vaccines")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE', 'ATTENDANT')")
    public ResponseEntity<VaccineReportResponse> getVaccineReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(required = false) String manufacturer) {
        return ResponseEntity.ok(vaccineReportService.getVaccineReport(from, to, manufacturer));
    }

    @GetMapping("/appointments")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE', 'ATTENDANT')")
    public AppointmentReportResponse getAppointmentReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(required = false) Long patientId,
        @RequestParam(required = false) AppointmentStatus status) {
        return appointmentReportService.getAppointmentReport(LocalDateTime.of(from, LocalTime.MIN), LocalDateTime.of(to, LocalTime.MAX), patientId, status);
    }
}
