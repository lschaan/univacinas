package com.univacinas.report;

import com.univacinas.vaccine.VaccineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {

    private final VaccineService vaccineService;

    public VaccineReportResponse getVaccineReport() {
        long totalVaccines = vaccineService.countVaccines();
        long distinctVaccineNames = vaccineService.countDistinctVaccinesByName();

        long expiredCount = vaccineService.countExpiredVaccines();
        long nearExpiryCount = vaccineService.countNearExpiryVaccines();
        long lowAmountCount = vaccineService.countLowAmountVaccines();

        List<ManufacturerBreakdown> byManufacturer = vaccineService.

        return VaccineReportResponse.builder()
            .generatedAt(LocalDate.now())
            .totalVaccines(totalVaccines)
            .distinctVaccineNames(distinctVaccineNames)
            .expiredCount(expiredCount)
            .nearExpiryCount(nearExpiryCount)
            .lowAmountCount(lowAmountCount)
            .byManufacturer(List.of())
            .build();
    }
}
