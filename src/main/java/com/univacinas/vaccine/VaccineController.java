package com.univacinas.vaccine;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Vaccine> createVaccine(@RequestBody CreateVaccineRequest request) {
        return ResponseEntity.ok(vaccineService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) {
        return ResponseEntity.ok(vaccineService.findById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vaccine>> listVaccines() {
        return ResponseEntity.ok(vaccineService.findAll());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody UpdateVaccineRequest request) {
        return ResponseEntity.ok(vaccineService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'NURSE')")
    public void deleteVaccine(@PathVariable Long id) {
        vaccineService.delete(id);
    }
}
