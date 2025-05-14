package com.univacinas.report;

import com.univacinas.common.MockController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
@MockController
public class ReportController {

    @GetMapping("/vaccines")
    public ResponseEntity<VaccineReportResponse> getVaccineReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
        @RequestParam Optional<String> manufacturer
    ) {
        return ResponseEntity.ok(VaccineReportResponse.builder().byManufacturer(List.of(new ManufacturerBreakdown())).build());
    }
}
