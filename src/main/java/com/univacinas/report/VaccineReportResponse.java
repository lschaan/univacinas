package com.univacinas.report;

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
public class VaccineReportResponse {

    private LocalDateTime generatedAt;

    private LocalDate from;
    private LocalDate to;

    private long totalVaccines;
    private long distinctVaccineNames;

    private long expiredCount;
    private long nearExpiryCount;
    private long lowAmountCount;

    private List<ManufacturerBreakdown> byManufacturer;
}
