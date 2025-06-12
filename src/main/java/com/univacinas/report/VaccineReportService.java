package com.univacinas.report;

import com.univacinas.vaccine.VaccineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VaccineReportService {

    private final VaccineService vaccineService;

    public VaccineReportResponse getVaccineReport(LocalDate from, LocalDate to, Optional<String> manufacturer) {
        if (manufacturer.isPresent()) {
            return getReportByManufacturer(from, to, manufacturer.get());
        }

        long totalVaccines = vaccineService.countVaccines(from, to);
        long distinctVaccineNames = vaccineService.countDistinctVaccinesByName(from, to);

        long expiredCount = vaccineService.countExpiredVaccines(from, to);
        long nearExpiryCount = vaccineService.countNearExpiryVaccines(from, to);
        long lowAmountCount = vaccineService.countLowAmountVaccines(from, to);

        List<ManufacturerBreakdown> byManufacturer = vaccineService.getManufacturerBreakdown(from, to);

        return VaccineReportResponse.builder()
            .generatedAt(LocalDate.now())
            .totalVaccines(totalVaccines)
            .distinctVaccineNames(distinctVaccineNames)
            .expiredCount(expiredCount)
            .nearExpiryCount(nearExpiryCount)
            .lowAmountCount(lowAmountCount)
            .byManufacturer(byManufacturer)
            .build();
    }

    private VaccineReportResponse getReportByManufacturer(LocalDate from, LocalDate to, String manufacturer) {
        long totalVaccines = vaccineService.countVaccines(from, to, manufacturer);
        long distinctVaccineNames = vaccineService.countDistinctVaccinesByName(from, to, manufacturer);

        long expiredCount = vaccineService.countExpiredVaccines(from, to, manufacturer);
        long nearExpiryCount = vaccineService.countNearExpiryVaccines(from, to, manufacturer);
        long lowAmountCount = vaccineService.countLowAmountVaccines(from, to, manufacturer);

        List<ManufacturerBreakdown> byManufacturer = vaccineService.getManufacturerBreakdown(from, to, manufacturer);

        return VaccineReportResponse.builder()
            .generatedAt(LocalDate.now())
            .totalVaccines(totalVaccines)
            .distinctVaccineNames(distinctVaccineNames)
            .expiredCount(expiredCount)
            .nearExpiryCount(nearExpiryCount)
            .lowAmountCount(lowAmountCount)
            .byManufacturer(byManufacturer)
            .build();
    }
}
