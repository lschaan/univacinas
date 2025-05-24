package com.univacinas.vaccine;

import com.univacinas.common.MockController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin
@MockController
public class VaccineController {

    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody CreateVaccineRequest vaccine) {
        return ResponseEntity.ok(new Vaccine());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> getVaccine(@PathVariable Long id) {
        return ResponseEntity.ok(new Vaccine());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Vaccine>> listVaccines() {
        return ResponseEntity.ok(List.of(new Vaccine()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vaccine> updateVaccine(@PathVariable Long id, @RequestBody UpdateVaccineRequest request) {
        return ResponseEntity.ok(new Vaccine());
    }

    @DeleteMapping("/{id}")
    public void deleteVaccine(@PathVariable Long id) {
    }
}
