package com.univacinas.vaccine;

import com.univacinas.error.VaccineNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return vaccineRepository.findAll();
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
}
