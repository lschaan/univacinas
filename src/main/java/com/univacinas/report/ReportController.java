package com.univacinas.report;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/report")
@CrossOrigin
@AllArgsConstructor
public class ReportController {

    private final VaccineReportService vaccineReportService;

    @GetMapping("/vaccines")
    public ResponseEntity<VaccineReportResponse> getVaccineReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam Optional<String> manufacturer
    ) {
        return ResponseEntity.ok(vaccineReportService.getVaccineReport(from, to, manufacturer));
    }
}
