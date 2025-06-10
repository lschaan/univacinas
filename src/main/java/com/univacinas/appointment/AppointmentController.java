package com.univacinas.appointment;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN')")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest request) {
        Appointment appointment = appointmentService.createAppointment(request);
        return ResponseEntity.ok(AppointmentResponse.from(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointment(id);
        return ResponseEntity.ok(AppointmentResponse.from(appointment));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppointmentResponse>> listAppointments(@RequestParam Optional<Long> patientId, @RequestParam Optional<AppointmentStatus> status) {
        List<Appointment> appointments = appointmentService.listAppointments(patientId, status);
        return ResponseEntity.ok(appointments.stream().map(AppointmentResponse::from).toList());
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN', 'NURSE')")
    public ResponseEntity<AppointmentResponse> cancelAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(AppointmentResponse.from(appointment));
    }

    @PostMapping("/complete/{id}")
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN', 'NURSE')")
    public ResponseEntity<AppointmentResponse> completeAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.completeAppointment(id);
        return ResponseEntity.ok(AppointmentResponse.from(appointment));
    }
}
