package com.univacinas.vaccine;

import com.univacinas.error.VaccineNotFoundException;
import com.univacinas.report.ManufacturerBreakdown;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;

    public Vaccine create(CreateVaccineRequest request) {
        Vaccine vaccine = Vaccine.builder()
            .name(request.getName())
            .batch(request.getBatch())
            .manufacturer(request.getManufacturer())
            .expirationDate(request.getExpirationDate())
            .amount(request.getAmount())
            .build();

        return vaccineRepository.save(vaccine);
    }

    public Vaccine findById(Long id) {
        return vaccineRepository.findById(id).orElseThrow(() -> new VaccineNotFoundException("Vacina não encontrada."));
    }

    public List<Vaccine> findAll() {
        List<Vaccine> vaccines = vaccineRepository.findAll();
        vaccines.sort(Comparator.comparing(Vaccine::getId));
        return vaccines;
    }

    public Vaccine update(Long id, UpdateVaccineRequest request) {
        Vaccine vaccine = findById(id);

        if (request.getName() != null) vaccine.setName(request.getName());
        if (request.getBatch() != null) vaccine.setBatch(request.getBatch());
        if (request.getManufacturer() != null) vaccine.setManufacturer(request.getManufacturer());
        if (request.getExpirationDate() != null) vaccine.setExpirationDate(request.getExpirationDate());
        if (request.getAmount() != null) vaccine.setAmount(request.getAmount());

        return vaccineRepository.save(vaccine);
    }

    public void delete(Long id) {
        vaccineRepository.deleteById(id);
    }

    public Vaccine reduceStock(Vaccine vaccine) {
        vaccine.reduceStock();
        return vaccineRepository.save(vaccine);
    }

    public List<ManufacturerBreakdown> getManufacturerBreakdown(LocalDate from, LocalDate to) {
        List<Vaccine> vaccines = vaccineRepository.findAllByCreationDateBetween(from, to);
        return getManufacturerBreakdown(vaccines);
    }

    public List<ManufacturerBreakdown> getManufacturerBreakdown(LocalDate from, LocalDate to, String manufacturer) {
        List<Vaccine> vaccines = vaccineRepository.findAllByManufacturerAndCreationDateBetween(manufacturer, from, to);
        return getManufacturerBreakdown(vaccines);
    }

    private List<ManufacturerBreakdown> getManufacturerBreakdown(List<Vaccine> vaccines) {
        return vaccines.stream()
            .collect(Collectors.groupingBy(Vaccine::getManufacturer, Collectors.summarizingInt(Vaccine::getAmount)))
            .entrySet()
            .stream()
            .map(e -> {
                IntSummaryStatistics stats = e.getValue();
                return new ManufacturerBreakdown(e.getKey(), stats.getSum(), stats.getCount());
            })
            .toList();
    }

    public long countVaccines(LocalDate from, LocalDate to) {
        return vaccineRepository.countByCreationDateBetween(from, to);
    }

    public long countVaccines(LocalDate from, LocalDate to, String manufacturer) {
        return vaccineRepository.countByCreationDateBetweenAndManufacturer(from, to, manufacturer);
    }

    public long countDistinctVaccinesByName(LocalDate from, LocalDate to) {
        return vaccineRepository.countDistinctNamesByCreationDateBetween(from, to);
    }

    public long countDistinctVaccinesByName(LocalDate from, LocalDate to, String manufacturer) {
        return vaccineRepository.countDistinctNamesByCreationDateBetweenAndManufacturer(from, to, manufacturer);
    }

    public long countExpiredVaccines(LocalDate from, LocalDate to) {
        return vaccineRepository.countByExpirationDateBeforeAndCreationDateBetween(LocalDate.now(), from, to);
    }

    public long countExpiredVaccines(LocalDate from, LocalDate to, String manufacturer) {
        return vaccineRepository.countByExpirationDateBeforeAndCreationDateBetweenAndManufacturer(LocalDate.now(), from, to, manufacturer);
    }

    public long countNearExpiryVaccines(LocalDate from, LocalDate to) {
        return vaccineRepository.countByExpirationDateBetweenAndCreationDateBetween(LocalDate.now(), LocalDate.now().plusWeeks(1L), from, to);
    }

    public long countNearExpiryVaccines(LocalDate from, LocalDate to, String manufacturer) {
        return vaccineRepository.countByExpirationDateBetweenAndCreationDateBetweenAndManufacturer(LocalDate.now(), LocalDate.now().plusWeeks(1L), from, to, manufacturer);
    }

    public long countLowAmountVaccines(LocalDate from, LocalDate to) {
        return vaccineRepository.countByAmountLessThanEqualAndCreationDateBetween(10, from, to);
    }

    public long countLowAmountVaccines(LocalDate from, LocalDate to, String manufacturer) {
        return vaccineRepository.countByAmountLessThanEqualAndCreationDateBetweenAndManufacturer(10, from, to, manufacturer);
    }
}
