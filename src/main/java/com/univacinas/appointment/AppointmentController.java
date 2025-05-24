package com.univacinas.appointment;

import com.univacinas.common.MockController;
import com.univacinas.user.User;
import com.univacinas.vaccine.Vaccine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
@MockController
public class AppointmentController {

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody CreateAppointmentRequest request) {
        return ResponseEntity.ok(mockAppointment());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(mockAppointment());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Appointment>> listAppointments(@RequestParam Optional<Long> patientId, @RequestParam Optional<AppointmentStatus> status) {
        return ResponseEntity.ok(List.of(mockAppointment()));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(mockAppointment());
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<Appointment> completeAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(mockAppointment());
    }

    private Appointment mockAppointment() {
        return Appointment.builder()
            .patient(new User())
            .nurse(new User())
            .vaccine(new Vaccine())
            .creationDate(LocalDateTime.now())
            .dateTime(LocalDateTime.now().plusDays(1L))
            .status(AppointmentStatus.SCHEDULED)
            .build();
    }
}
