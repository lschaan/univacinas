package com.univacinas.report;

import com.univacinas.vaccine.VaccineRepository;
import com.univacinas.vaccine.VaccineStats;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class VaccineReportService {

    private final VaccineRepository vaccineRepository;

    public VaccineReportResponse getVaccineReport(LocalDate from, LocalDate to, String manufacturer) {
        VaccineStats stats = vaccineRepository.fetchStats(from, to, manufacturer, LocalDate.now(), LocalDate.now().plusWeeks(1), 10);
        List<ManufacturerBreakdown> breakdown = vaccineRepository.fetchBreakdown(from, to, manufacturer);

        return VaccineReportResponse.builder()
            .generatedAt(LocalDateTime.now())
            .from(from)
            .to(to)
            .totalVaccines(stats.getTotal())
            .distinctVaccineNames(stats.getDistinctNames())
            .expiredCount(stats.getExpired())
            .nearExpiryCount(stats.getNearExpiry())
            .lowAmountCount(stats.getLowAmount())
            .byManufacturer(breakdown)
            .build();
    }
}
