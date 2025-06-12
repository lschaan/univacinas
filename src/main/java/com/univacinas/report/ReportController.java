package com.univacinas.report;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/report")
@CrossOrigin
@AllArgsConstructor
public class ReportController {

    private final VaccineReportService vaccineReportService;

    @GetMapping("/vaccines")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE', 'ATTENDANT')")
    public ResponseEntity<VaccineReportResponse> getVaccineReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam(required = false) String manufacturer) {
        return ResponseEntity.ok(vaccineReportService.getVaccineReport(from, to, manufacturer));
    }
}
