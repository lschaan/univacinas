package com.univacinas.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaccineReportResponse {

    private LocalDate generatedAt;

    private long totalVaccines;
    private long distinctVaccineNames;

    private long expiredCount;
    private long nearExpiryCount;
    private long lowAmountCount;

    private List<ManufacturerBreakdown> byManufacturer;
}
