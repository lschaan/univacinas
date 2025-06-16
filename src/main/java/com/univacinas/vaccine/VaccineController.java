package com.univacinas.vaccine;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin
@AllArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public Vaccine createVaccine(@RequestBody CreateVaccineRequest request) {
        return vaccineService.create(request);
    }

    @GetMapping("/{id}")
    public Vaccine getVaccine(@PathVariable Long id) {
        return vaccineService.findById(id);
    }

    @GetMapping("/list")
    public List<Vaccine> listVaccines() {
        return vaccineService.findAll();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public Vaccine updateVaccine(@PathVariable Long id, @RequestBody UpdateVaccineRequest request) {
        return vaccineService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public void deleteVaccine(@PathVariable Long id) {
        vaccineService.delete(id);
    }
}
