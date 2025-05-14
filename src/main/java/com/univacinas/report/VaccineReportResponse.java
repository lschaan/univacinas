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

    private int totalVaccines;
    private int distinctVaccineNames;

    private int expiredCount;
    private int nearExpiryCount;
    private int lowAmountCount;

    private List<ManufacturerBreakdown> byManufacturer;
}
